package com.barban.controller;

import java.beans.PropertyEditorSupport;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.barban.model.Language;
import com.barban.model.State;
import com.barban.model.User;
import com.barban.service.LanguageService;
import com.barban.service.UserService;

@Controller
@RequestMapping("/")
public class AppController {

	public static final Logger LOG = LoggerFactory.getLogger(AppController.class);

	@Autowired
	UserService userService;

	@Autowired
	LanguageService languageService;

	@Autowired
	MessageSource messageSource;

	@Transactional
	@RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
	public String listUsers(ModelMap model) {
		List<Language> languages = languageService.findAllLanguages(User.class);
		LOG.info(String.format("size of languages: %s", languages.size()));
		//languages.stream().forEach(l -> Hibernate.initialize(l.getUsers()));
		List<User> users = userService.findAllUsers();
		LOG.info(String.format("size of users: %s", users.size()));
		model.addAttribute("users", users);
		return "allusers";
	}

	@RequestMapping(value = { "/new" }, method = RequestMethod.GET)
	public String newUser(ModelMap model) {
		List<Language> languages = languageService.findAllLanguages();
		LOG.info(String.format("list of languages: %s", languages.size()));
		model.addAttribute("languages", languages);
		User user = new User();
		model.addAttribute("user", user);
		model.addAttribute("edit", false);
		return "registration";
	}

	@Transactional
	@RequestMapping(value = { "/new" }, method = RequestMethod.POST)
	public String saveUser(@Valid User user, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			List<Language> languages = languageService.findAllLanguages();
			LOG.info(String.format("size of languages: %s", languages.size()));
			//languages.stream().forEach(l -> Hibernate.initialize(l.getUsers()));
			model.addAttribute("languages", languages);
			LOG.info(String.format("Add user has errors %s", result.getAllErrors()));
			return "registration";
		}

		user.setState(State.ACTIVE.getState());
		userService.saveUser(user);
		LOG.info(String.format("Language for user: %s", user.getLanguage()));
		model.addAttribute("success", "User " + user.getName() + " registered successfully");
		LOG.info(String.format("Add new user: %s", user));
		return "success";
	}

	@Transactional
	@RequestMapping(value = { "/edit-{login}-user" }, method = RequestMethod.GET)
	public String editUser(@PathVariable String login, ModelMap model) {
		List<Language> languages = languageService.findAllLanguages();
		LOG.info(String.format("size of languages: %s", languages.size()));
		languages.stream().forEach(l -> Hibernate.initialize(l.getUsers()));
		User user = userService.findUserByLogin(login);
		if (getPrincipal().getUsername().equals(user.getLogin())) {
			model.addAttribute("languages", languages);
			model.addAttribute("user", user);
			model.addAttribute("edit", true);
			return "registration";
		} else {
			LOG.info(String.format("Editing user denied - wrong authentication:  login:%s;",
					getPrincipal().getUsername()));
			return "accessDenied";
		}
	}

	@Transactional
	@RequestMapping(value = { "/edit-{login}-user" }, method = RequestMethod.POST)
	public String updateUser(@Valid User user, BindingResult result, ModelMap model, @PathVariable String login) {

		if (result.hasErrors()) {
			LOG.info(String.format("ERROR edit-%s-user: %s", user.getLogin(), result.getAllErrors()));
			List<Language> languages = languageService.findAllLanguages();
			LOG.info(String.format("size of languages: %s", languages.size()));
			languages.stream().forEach(l -> Hibernate.initialize(l.getUsers()));
			model.addAttribute("languages", languages);
			model.addAttribute("edit", true);
			return "registration";
		}
		LOG.info(String.format("Edit-%s-user with language: %s", user.getLogin(), user.getLanguage().getName()));
		userService.updateUser(user);

		model.addAttribute("success", "User " + user.getName() + " updated successfully");
		return "success";
	}

	@RequestMapping(value = { "/delete-{login}-user" }, method = RequestMethod.GET)
	public String deleteUser(@PathVariable String login) {
		userService.deleteUserByLogin(login);
		return "redirect:/list";
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String adminPage(ModelMap model) {
		model.addAttribute("user", Optional.ofNullable(getPrincipal().getUsername()));
		return "admin";
	}

	@RequestMapping(value = "/db", method = RequestMethod.GET)
	public String dbaPage(ModelMap model) {
		model.addAttribute("user", Optional.ofNullable(getPrincipal().getUsername()));
		return "dba";
	}

	@RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
	public String accessDeniedPage(ModelMap model) {
		model.addAttribute("user", Optional.ofNullable(getPrincipal().getUsername()));
		return "accessDenied";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage() {
		return "login";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout";
	}

	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {

		binder.registerCustomEditor(Language.class, "language", new PropertyEditorSupport() {

			public void setAsText(String text) {
				Integer id = Integer.parseInt(text);
				Language language = (Language) languageService.findById(id);
				setValue(language);
			}
		});

	}

	private UserDetails getPrincipal() {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			return (UserDetails) principal;
		} else {
			LOG.info(String.format("ERROR getPrincipal: %s", principal.toString()));
			return null;
		}

	}

	

}

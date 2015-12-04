package com.barban.springmvc.controller;

import java.beans.PropertyEditorSupport;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.barban.springmvc.model.Language;
import com.barban.springmvc.model.User;
import com.barban.springmvc.service.LanguageService;
import com.barban.springmvc.service.UserService;

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
		List<Language> languages = languageService.findAllLanguages();
		LOG.info(String.format("size of languages: %s", languages.size()));
		languages.stream().forEach(l -> Hibernate.initialize(l.getUsers()));
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
			languages.stream().forEach(l -> Hibernate.initialize(l.getUsers()));
			model.addAttribute("languages", languages);
			LOG.info(String.format("Add user has errors %s", result.getAllErrors()));
			return "registration";
		}

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
		model.addAttribute("languages", languages);
		User user = userService.findUserByLogin(login);
		model.addAttribute("user", user);
		model.addAttribute("edit", true);
		return "registration";
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

}

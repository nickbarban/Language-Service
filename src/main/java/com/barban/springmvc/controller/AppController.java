package com.barban.springmvc.controller;

import java.beans.PropertyEditorSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.validation.Valid;

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

import com.barban.springmvc.dto.LanguageDTO;
import com.barban.springmvc.dto.UserDTO;
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

	/*
	 * This method will list all existing users.
	 */

	@RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
	public String listUsers(ModelMap model) {
		List<Language> languages = languageService.findAllLanguages();
		LOG.info(String.format("size of languages: %s", languages.size()));
		LOG.info(String.format("list of languages: %s", languages));
		List<LanguageDTO> languagesDTO = new ArrayList<>();
		languages.stream().forEach(l -> {
			LOG.info(String.format("Language from findAllLanguages: %s", l.toString()));
			LanguageDTO languageDTO = new LanguageDTO();
			languageDTO.setId(l.getId());
			languageDTO.setName(l.getName());
			languageDTO.setUsers(userService.findUsersByLanguage(l.getId()));
			languagesDTO.add(languageDTO);
		});
		LOG.info(String.format("size of languagesDTO: %s", languagesDTO.size()));
		LOG.info(String.format("list of languagesDTO: %s", languagesDTO));
		List<User> users = userService.findAllUsers();
		LOG.info(String.format("size of users: %s", users.size()));
		LOG.info(String.format("list of users: %s", users));
		List<UserDTO> usersDTO = users.stream().map(new Function<User, UserDTO>() {

			@Override
			public UserDTO apply(User u) {
				UserDTO userDTO = new UserDTO();
				userDTO.setId(u.getId());
				userDTO.setName(u.getName());
				userDTO.setLogin(u.getLogin());
				userDTO.setPassword(u.getPassword());
				userDTO.setLanguage(languagesDTO.stream().filter(l -> l.containsUser(u.getLogin())).findAny().get());
				return userDTO;
			}
		}).collect(Collectors.toList());
		model.addAttribute("users", usersDTO);
		LOG.info(String.format("size of usersDTO: %s", usersDTO.size()));
		LOG.info(String.format("list of usersDTO: %s", usersDTO));

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

	@RequestMapping(value = { "/new" }, method = RequestMethod.POST)
	public String saveUser(@Valid User user, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			LOG.info(String.format("Add user has errors %s", result.getAllErrors()));
			return "registration";
		}

		/*
		 * if (!userService.isUserLoginUnique(user.getId(), user.getLogin())) {
		 * FieldError loginError = new FieldError("user", "login",
		 * messageSource.getMessage("non.unique.login", new String[] {
		 * user.getLogin() }, Locale.getDefault()));
		 * result.addError(loginError); return "registration"; }
		 */

		// user.setLanguage(languageService.findById(1));

		userService.saveUser(user);
		LOG.info(String.format("Language for user: %s", user.getLanguage()));
		model.addAttribute("success", "User " + user.getName() + " registered successfully");
		LOG.info(String.format("Add new user: %s", user));
		return "success";
	}

	/*
	 * This method will provide the medium to update an existing user.
	 */
	@RequestMapping(value = { "/edit-{login}-user" }, method = RequestMethod.GET)
	public String editUser(@PathVariable String login, ModelMap model) {
		User user = userService.findUserByLogin(login);
		model.addAttribute("user", user);
		model.addAttribute("edit", true);
		return "registration";
	}

	/*
	 * This method will be called on form submission, handling POST request for
	 * updating user in database. It also validates the user's input
	 */
	@RequestMapping(value = { "/edit-{login}-user" }, method = RequestMethod.POST)
	public String updateUser(@Valid User user, BindingResult result, ModelMap model, @PathVariable String login) {

		if (result.hasErrors()) {
			return "registration";
		}

		/*
		 * if (!userService.isUserLoginUnique(user.getId(), user.getLogin())) {
		 * FieldError loginError = new FieldError("user", "login",
		 * messageSource.getMessage("non.unique.login", new String[] {
		 * user.getLogin() }, Locale.getDefault()));
		 * result.addError(loginError); return "registration"; }
		 */

		userService.updateUser(user);

		model.addAttribute("success", "User " + user.getName() + " updated successfully");
		return "success";
	}

	/*
	 * This method will delete a user by it's LOGIN value.
	 */
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

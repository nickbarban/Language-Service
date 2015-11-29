package com.barban.springmvc.controller;

import java.beans.PropertyEditorSupport;
import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;

import javax.validation.Valid;

import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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

	/*
	 * This method will list all existing users.
	 */
	@RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
	public String listUsers(ModelMap model) {
/*
		List<Language> languages = languageService.findAllLanguages();
		// Hibernate.initialize(users);
		languages.stream().forEach(new Consumer<Language>() {

			@Override
			public void accept(Language l) {
				Hibernate.initialize(l.getUsers());
			}
		});*/
		List<User> users = userService.findAllUsers();
		model.addAttribute("users", users);
		LOG.info(String.format("list of users: %s", users.size()));
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

		if (!userService.isUserLoginUnique(user.getId(), user.getLogin())) {
			FieldError loginError = new FieldError("user", "login", messageSource.getMessage("non.unique.login",
					new String[] { user.getLogin() }, Locale.getDefault()));
			result.addError(loginError);
			return "registration";
		}

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

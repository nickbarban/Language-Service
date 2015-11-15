package com.barban.springmvc.controller;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.barban.springmvc.model.User;
import com.barban.springmvc.service.UserService;

@Controller
@RequestMapping("/")
public class AppController {

	@Autowired
	UserService service;

	@Autowired
	MessageSource messageSource;

	/*
	 * This method will list all existing users.
	 */
	@RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
	public String listUsers(ModelMap model) {

		List<User> users = service.findAllUsers();
		model.addAttribute("users", users);
		return "allusers";
	}

	/*
	 * This method will provide the medium to add a new user.
	 */
	@RequestMapping(value = { "/new" }, method = RequestMethod.GET)
	public String newUser(ModelMap model) {
		User user = new User();
		model.addAttribute("user", user);
		model.addAttribute("edit", false);
		return "registration";
	}

	/*
	 * This method will be called on form submission, handling POST request for
	 * saving user in database. It also validates the user's input
	 */
	@RequestMapping(value = { "/new" }, method = RequestMethod.POST)
	public String saveUser(@Valid User user, BindingResult result, ModelMap model) {

		if (result.hasErrors()) {
			return "registration";
		}

		/*
		 * Preferred way to achieve uniqueness of field [login] should be
		 * implementing custom @Unique annotation and applying it on field
		 * [login] of Model class [User].
		 * 
		 * Below mentioned peace of code [if block] is to demonstrate that you
		 * can fill custom errors outside the validation framework as well while
		 * still using internationalized messages.
		 * 
		 */
		if (!service.isUserLoginUnique(user.getId(), user.getLogin())) {
			FieldError loginError = new FieldError("user", "login", messageSource.getMessage("non.unique.login",
					new String[] { user.getLogin() }, Locale.getDefault()));
			result.addError(loginError);
			return "registration";
		}

		service.saveUser(user);
		

		model.addAttribute("success", "User " + user.getName() + " registered successfully");
		return "success";
	}

	/*
	 * This method will provide the medium to update an existing user.
	 */
	@RequestMapping(value = { "/edit-{login}-user" }, method = RequestMethod.GET)
	public String editUser(@PathVariable String login, ModelMap model) {
		User user = service.findUserByLogin(login);
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

		if (!service.isUserLoginUnique(user.getId(), user.getLogin())) {
			FieldError loginError = new FieldError("user", "login", messageSource.getMessage("non.unique.login",
					new String[] { user.getLogin() }, Locale.getDefault()));
			result.addError(loginError);
			return "registration";
		}

		service.updateUser(user);
		;

		model.addAttribute("success", "User " + user.getName() + " updated successfully");
		return "success";
	}

	/*
	 * This method will delete a user by it's LOGIN value.
	 */
	@RequestMapping(value = { "/delete-{login}-user" }, method = RequestMethod.GET)
	public String deleteUser(@PathVariable String login) {
		service.deleteUserByLogin(login);
		return "redirect:/list";
	}

}

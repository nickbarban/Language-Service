package com.barban.springmvc.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.context.MessageSource;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;

import com.barban.springmvc.controller.AppController;
import com.barban.springmvc.model.Language;
import com.barban.springmvc.model.User;
import com.barban.springmvc.service.LanguageService;
import com.barban.springmvc.service.UserService;
import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.atLeastOnce;

public class AppControllerTest {

	private final String[] languageNames = { "english", "russian", "deutch", "italian", "french", "spanish",
			"ukrainian" };

	private final String[] names = { "Ivan", "Petr", "Sidor" };

	@Mock
	UserService userService;

	@Mock
	LanguageService languageService;

	@Mock
	MessageSource message;

	@InjectMocks
	AppController appController;

	@Spy
	List<User> users = new ArrayList<>();

	@Spy
	List<Language> languages = new ArrayList<>();

	@Spy
	ModelMap model;

	@Mock
	BindingResult result;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		languages = getLanguagesList();
		users = getUsersList();
	}

	/*
	 * @Test public void listUsers() {
	 * when(userService.findAllUsers()).thenReturn(users);
	 * Assert.assertEquals(appController.listUsers(model), "allusers");
	 * Assert.assertEquals(model.get("users"), users); verify(userService,
	 * atLeastOnce()).findAllUsers(); }
	 */

	/*@Test
	public void newUser() {
		when(languageService.findAllLanguages()).thenReturn(languages);
		Assert.assertEquals(appController.newUser(model), "registration");
		Assert.assertEquals(model.get("languages"), languages);
		Assert.assertNotNull(model.get("user"));
		Assert.assertFalse(Boolean.valueOf((Boolean) model.get("edit")));
	}*/

	/*
	 * @Test public void saveUserWithValidationError() {
	 * 
	 * }
	 */

	/*
	 * @Test public void saveUserWithValidationErrorNonUniqueLogin() {
	 * 
	 * }
	 */

	/*
	 * @Test public void saveUserWithSuccess() {
	 * 
	 * }
	 */

	/*
	 * @Test public void editUser() {
	 * 
	 * }
	 */

	/*
	 * @Test public void updateUserWithValidationError() {
	 * 
	 * }
	 */

	/*
	 * @Test public void updateUserWithValidationErrorNonUniqueLogin() {
	 * 
	 * }
	 */

	/*
	 * @Test public void updateUserWithSuccess() {
	 * 
	 * }
	 */

	/*
	 * @Test public void deleteEmployee() {
	 * 
	 * }
	 */

	private List<User> getUsersList() {
		List<User> result = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			result.add(new User(i + 1, names[i], names[i].substring(0, 3) + "login",
					names[i].substring(0, 3) + "password", languages.get(i)));
		}
		return null;
	}

	private List<Language> getLanguagesList() {
		List<Language> result = new ArrayList<>();
		for (int i = 0; i < languageNames.length; i++) {
			result.add(new Language(i + 1, languageNames[i]));
		}
		return result;
	}

}

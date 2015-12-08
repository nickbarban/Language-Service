package com.barban.service;

import java.util.List;
import java.util.Set;

import com.barban.model.User;


public interface UserService {

	User findById(int id);

	void saveUser(User user);

	void updateUser(User user);

	void deleteUserByLogin(String login);

	List<User> findAllUsers();

	User findUserByLogin(String login);

	boolean isUserLoginUnique(Integer id, String login);

	Set<User> findUsersByLanguage(int id);
}

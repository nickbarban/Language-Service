package com.barban.dao;

import java.util.List;

import com.barban.model.User;


public interface UserDao {

	User findById(int id);

	void saveUser(User user);

	void deleteUserByLogin(String login);

	List<User> findAllUsers();

	User findUserByLogin(String login);

	List<User> findUsersByLanguage(int id);
}

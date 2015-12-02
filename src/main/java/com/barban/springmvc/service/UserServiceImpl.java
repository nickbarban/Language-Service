package com.barban.springmvc.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.barban.springmvc.dao.UserDao;
import com.barban.springmvc.model.User;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao dao;

	@Override
	public User findById(int id) {
		return dao.findById(id);
	}

	@Override
	public void saveUser(User user) {
		dao.saveUser(user);

	}

	/*
	 * Since the method is running with Transaction, No need to call hibernate
	 * update explicitly. Just fetch the entity from db and update it with
	 * proper values within transaction. It will be updated in db once
	 * transaction ends.
	 */
	@Override
	public void updateUser(User user) {
		User entity = dao.findById(user.getId());
		if (entity != null) {
			entity.setName(user.getName());
			entity.setLogin(user.getLogin());
			entity.setPassword(user.getPassword());
		}

	}

	@Override
	public void deleteUserByLogin(String login) {
		dao.deleteUserByLogin(login);

	}

	@Override
	public List<User> findAllUsers() {
		return dao.findAllUsers();
	}

	@Override
	public User findUserByLogin(String login) {
		return dao.findUserByLogin(login);
	}

	@Override
	public boolean isUserLoginUnique(Integer id, String login) {
		User user = findUserByLogin(login);
		return (user == null || ((id != null) && (user.getId() == id)));
	}

	@Override
	public Set<User> findUsersByLanguage(int id) {
		return new HashSet<>(dao.findUsersByLanguage(id));
	}

}

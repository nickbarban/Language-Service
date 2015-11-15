package com.barban.springmvc.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.barban.springmvc.model.User;

@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {

	@Override
	public User findById(int id) {
		return getByKey(id);
	}

	@Override
	public void saveUser(User user) {
		persist(user);

	}

	@Override
	public void deleteUserByLogin(String login) {
		Query query = getSession().createSQLQuery("delete from user where login = :login");
		query.setString("login", login);
		query.executeUpdate();

	}

	@Override
	public List<User> findAllUsers() {
		Criteria criteria = createEntityCriteria();
		return (List<User>) criteria.list();
	}

	@Override
	public User findUserByLogin(String login) {
		Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("login", login));
		return (User) criteria.uniqueResult();
	}

}

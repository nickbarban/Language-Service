package com.barban.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.barban.model.User;

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
		Query query = getSession().createSQLQuery("delete from USER where login = :login");
		query.setString("login", login);
		query.executeUpdate();

	}

	@SuppressWarnings("unchecked")
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

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findUsersByLanguage(int id) {
		Query query = getSession().createQuery("FROM User WHERE id_lang = :id");
		query.setParameter("id", id);
		return query.list();
	}

}

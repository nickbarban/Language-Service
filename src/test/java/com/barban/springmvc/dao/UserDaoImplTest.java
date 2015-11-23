package com.barban.springmvc.dao;

import org.dbunit.dataset.CompositeDataSet;
import org.dbunit.dataset.IDataSet;

import com.barban.springmvc.dao.EntityDaoImplTest;
import com.barban.springmvc.dao.LanguageDao;
import com.barban.springmvc.dao.UserDao;
import com.barban.springmvc.model.Language;
import com.barban.springmvc.model.User;

import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserDaoImplTest extends EntityDaoImplTest {

	@Autowired
	UserDao userDao;

	@Autowired
	LanguageDao languageDao;

	@Override
	protected IDataSet getDataSet() throws Exception {
		IDataSet[] datasets = {
				new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("Language.xml")),
				new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("User.xml")) };
		return new CompositeDataSet(datasets);
	}

	@Test
	public void findById() {
		Assert.assertNotNull(userDao.findById(1));
		Assert.assertNull(userDao.findById(3));
	}

	@Test
	public void saveUser() {
		userDao.saveUser(getSampleUser());
		Assert.assertEquals(userDao.findAllUsers().size(), 3);
	}

	/*
	 * @Test public void deleteEmployeeBySsn() {
	 * userDao.deleteUserByLogin("SAMYLOGIN");
	 * Assert.assertEquals(userDao.findAllUsers().size(), 1); }
	 */

	/*
	 * @Test public void deleteEmployeeByInvalidSsn() {
	 * userDao.deleteUserByLogin("qwerty");
	 * Assert.assertEquals(userDao.findAllUsers().size(), 2); }
	 */

	/*
	 * @Test public void findAllEmployees() {
	 * Assert.assertEquals(userDao.findAllUsers().size(), 2); }
	 */

	/*
	 * @Test public void findEmployeeBySsn() {
	 * Assert.assertNotNull(userDao.findUserByLogin("SAMYLOGIN"));
	 * Assert.assertNull(userDao.findUserByLogin("qwerty")); }
	 */

	public User getSampleUser() {
		User user = new User();
		user.setId(3);
		user.setName("Karen");
		user.setLogin("KarenLogin");
		user.setPassword("3333");
		Language language = new Language(10, "turkish");
		language.getUsers().add(user);
		user.setLanguage(language);
		return user;
	}

}

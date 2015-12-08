package com.barban.dao;

import org.dbunit.dataset.CompositeDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.barban.model.State;
import com.barban.model.User;

public class UserDaoImplTest extends EntityDaoImplTest {

	public static final Logger LOG = LoggerFactory.getLogger(UserDaoImplTest.class);

	@Autowired
	UserDao userDao;

	@Autowired
	LanguageDao languageDao;

	@Override
	protected IDataSet getDataSet() throws Exception {
		LOG.info("Set up for logging");
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

	@Test
	public void deleteEmployeeBySsn() {
		userDao.deleteUserByLogin("SAMYLOGIN");
		Assert.assertEquals(userDao.findAllUsers().size(), 1);
	}

	@Test
	public void deleteEmployeeByInvalidSsn() {
		userDao.deleteUserByLogin("qwerty");
		Assert.assertEquals(userDao.findAllUsers().size(), 2);
	}

	@Test
	public void findAllEmployees() {
		Assert.assertEquals(userDao.findAllUsers().size(), 2);
	}

	@Test
	public void findEmployeeBySsn() {
		Assert.assertNotNull(userDao.findUserByLogin("SAMYLOGIN"));
		Assert.assertNull(userDao.findUserByLogin("qwerty"));
	}

	public User getSampleUser() {
		User user = new User();
		user.setName("Karen");
		user.setLogin("KarenLogin");
		user.setPassword("3333");
		user.setEmail("Karen@KarenLogin");
		user.setState(State.ACTIVE.getState());;
		user.setLanguage(languageDao.findById(1));
		return user;
	}

}

package com.barban.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.barban.model.Language;


@Repository("languageDao")
public class LanguageDaoImpl extends AbstractDao<Integer, Language> implements LanguageDao {

	@Override
	public Language findById(int id) {
		return getByKey(id);
	}

	@Override
	public void saveLanguage(Language language) {
		persist(language);

	}

	@Override
	public void deleteLanguageByName(String name) {
		Query query = getSession().createSQLQuery("delete from language where name = :name");
		query.setString("name", name);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Language> findAllLanguages() {
		Criteria criteria = createEntityCriteria();
		return (List<Language>) criteria.list();
	}

	@Override
	public Language findLanguageByName(String name) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("name", name));
		return (Language) criteria.uniqueResult();
	}

}

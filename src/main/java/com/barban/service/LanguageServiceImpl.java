package com.barban.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.barban.dao.LanguageDao;
import com.barban.model.Language;
import com.barban.model.User;

@Service("languageService")
@Transactional
public class LanguageServiceImpl extends Initializator implements LanguageService {

	@Autowired
	private LanguageDao dao;

	@Override
	public Language findById(int id) {
		return dao.findById(id);
	}

	@Override
	public void saveLanguage(Language language) {
		dao.saveLanguage(language);
	}

	@Override
	public void updateLanguage(Language language) {
		Language entity = dao.findById(language.getId());
		if (entity != null) {
			entity.setName(language.getName());
		}
	}

	@Override
	public void deleteLanguageByName(String name) {
		// dao.deleteLanguageByName(name);

	}

	@Override
	public List<Language> findAllLanguages(Class<?>... dependencies) {
		List<Language> languages = dao.findAllLanguages();
		languages.stream().forEach(l -> initializeSets(l, dependencies));
		return languages;
	}

	@Override
	public Language findLanguageByName(String name) {
		return dao.findLanguageByName(name);
	}

	@Override
	public boolean isLanguageNameUnique(Integer id, String name) {
		Language language = findLanguageByName(name);
		return (language == null || ((id != null) && (language.getId() == id)));
	}

	@Override
	public Set<User> getAllUsersByLanguage(int languageId) {
		Language language = findById(languageId);
		return language.getUsers();
	}

}

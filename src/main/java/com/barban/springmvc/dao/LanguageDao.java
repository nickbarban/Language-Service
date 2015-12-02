package com.barban.springmvc.dao;

import java.util.List;

import com.barban.springmvc.model.Language;

public interface LanguageDao {

	Language findById(int id);

	void saveLanguage(Language language);

	void deleteLanguageByName(String name);

	List<Language> findAllLanguages();

	Language findLanguageByName(String name);

	List<Language> findAllLanguagesAlt();

	
}

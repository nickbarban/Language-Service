package com.barban.dao;

import java.util.List;

import com.barban.model.Language;

public interface LanguageDao {

	Language findById(int id);

	void saveLanguage(Language language);

	void deleteLanguageByName(String name);

	List<Language> findAllLanguages();

	Language findLanguageByName(String name);

	
}

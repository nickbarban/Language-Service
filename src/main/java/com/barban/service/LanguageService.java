package com.barban.service;

import java.util.List;
import java.util.Set;

import com.barban.model.Language;
import com.barban.model.User;

public interface LanguageService {

	Language findById(int id);

	void saveLanguage(Language language);

	void updateLanguage(Language language);

	void deleteLanguageByName(String name);

	List<Language> findAllLanguages(Class<?>...dependencies);

	Language findLanguageByName(String name);

	boolean isLanguageNameUnique(Integer id, String name);

	public Set<User> getAllUsersByLanguage(int languageId);

}

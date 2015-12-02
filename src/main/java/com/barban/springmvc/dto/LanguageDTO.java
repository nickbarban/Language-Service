package com.barban.springmvc.dto;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.function.Predicate;

import com.barban.springmvc.model.User;

public class LanguageDTO {

	private int id;
	private String name;
	private Set<User> users = new HashSet<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LanguageDTO other = (LanguageDTO) obj;
		if (id != other.getId())
			return false;
		if (name == null) {
			if (other.getName() != null)
				return false;
		} else if (!name.equals(other.getName()))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return name.toUpperCase(Locale.getDefault());
	}

	public LanguageDTO() {
		// TODO Auto-generated constructor stub
	}

	public boolean containsUser(String userLogin) {
		return users.stream().anyMatch(new Predicate<User>() {

			@Override
			public boolean test(User t) {
				return t.getLogin().equals(userLogin);
			}
		});
	}
}

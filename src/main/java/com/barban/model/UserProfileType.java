package com.barban.model;

public enum UserProfileType {

	USER("USER"), DBA("DBA"), ADMIN("ADMIN");

	public String userProfileType;

	private UserProfileType(String userProfileType) {
		this.userProfileType = userProfileType;
	}

	public String getUserProfileType() {
		return userProfileType;
	}
	
	
}

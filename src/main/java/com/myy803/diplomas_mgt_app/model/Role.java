package com.myy803.diplomas_mgt_app.model;

public enum Role {
	STUDENT("Student"),
	PROFESSOR("Professor");
	
	private final String role;
	
	private Role(String role){
		this.role = role;
	}
	
	public String getValue() {
		return role;
	}
}

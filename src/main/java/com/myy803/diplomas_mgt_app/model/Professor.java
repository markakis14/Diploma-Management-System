package com.myy803.diplomas_mgt_app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="professors")
public class Professor {
	
	@Id 
	@Column(name="id")
	private int id;
	
	@Column(name="full_name")
	private String fullName;
	
	@Column(name="specialty")
	private String specialty;
	
	public Professor() {
		id = -1;
	}
	
	public Professor(int id) {
		this.id=id;
	}
	
	public String getFullName() {
		return fullName;
	}
	
	public String getSpecialty() {
		return specialty;
	}
	
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}
}

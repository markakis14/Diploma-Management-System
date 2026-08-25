package com.myy803.diplomas_mgt_app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="applications")
public class Application {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@ManyToOne
	private Student applicant;
	
	@ManyToOne
	private Thesis thesis;
	
	
	public Student getApplicant() {
		return applicant;
	}
	
	public void setApplicant(Student applicant) {
		this.applicant = applicant;
		applicant.addApplication(this);
	}
	
	public Thesis getThesis() {
		return thesis;
	}
	
	public void setThesis(Thesis thesis) {
		this.thesis=thesis;
		thesis.addApplication(this);
	}
	
	public int getId() {
		return id;
	}
	
	public int getThesisId() {
		return thesis.getId();
	}
	
	public int getStudentId(){
		return applicant.getId();
	}
}

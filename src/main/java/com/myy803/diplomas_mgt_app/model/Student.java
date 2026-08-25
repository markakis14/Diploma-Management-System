package com.myy803.diplomas_mgt_app.model;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="students")
public class Student{
	
	public Student() {
		
	}
	
	public Student(int id) {
		this.id = id;
	}
	
	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="full_name")
	private String fullName;
	
	@Column(name="years_of_studies")
	private int yearsOfStudies;
	
	@Column(name="current_avg_grade")
	private double currentAvgGrade;
	
	@Column(name="courses_remaining")
	private int coursesRemaining;
	
	@OneToMany(mappedBy="applicant")
	private List<Application> applications;
	
	public String getFullName() {
		return fullName;
	}
	
	public int getYearsOfStudies() {
		return yearsOfStudies;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getCoursesRemaining() {
		return coursesRemaining;
	}
	
	public double getCurrentAvgGrade() {
		return currentAvgGrade;
	}
	
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	public void setYearsOfStudies(int yearsOfStudies) {
		this.yearsOfStudies = yearsOfStudies;
	}
	
	public void setCoursesRemaining(int coursesRemaining) {
		this.coursesRemaining = coursesRemaining;
	}
	
	public void setCurrentAvgGrade(double currentAvgGrade) {
		this.currentAvgGrade = currentAvgGrade;
	}
	
	public List<Application> getApplications(){
		return applications;
	}
	
	public void setApplications(List<Application> applications) {
		this.applications = applications;
	}
	
	public void addApplication(Application app) {
		this.applications.add(app);
	}
	
	public boolean alreadyApplied(int thesisId) {
		for (Application application: applications) {
			if (application.getThesisId() == thesisId)
				return true;
		}
		return false;
	}
}

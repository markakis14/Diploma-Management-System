package com.myy803.diplomas_mgt_app.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="thesis")
public class Thesis {
	
	public Thesis() {
		this.professorId = -1;
	}
	
	public Thesis(int profId) {
		this.professorId = profId;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="professor_id")
	private int professorId;

	@Column(name="title")
	private String title;
	
	@Column(name="description")
	private String description;
	
	@OneToMany(mappedBy="thesis", cascade = CascadeType.REMOVE)
    private List<Application> applications;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getProfessorId() {
		return professorId;
	}
	
	public void setProfessorId(int professorId) {
		this.professorId = professorId;
	}
	
	public List<Application> getApplications() {
		return applications;
	}
	
	public void setApplications(List<Application> applications) {
		this.applications = applications;
	}
	
	public void addApplication(Application application) {
		applications.add(application);
	}
	
	
	public Student getBestApplicant(BestApplicantStrategy strategy) {
		return strategy.findBestApplicant(applications);
	}

}

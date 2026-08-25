package com.myy803.diplomas_mgt_app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="assigned_thesis")
public class AssignedThesis {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="title")
	private String title;
	
	@Column(name="implementation_grade")
	private double implementationGrade;
	
	@Column(name="report_grade")
	private double reportGrade;
	
	@Column(name="presentation_grade")
	private double presentationGrade;
	
	@Column(name="student_name")
	private String studentName;
	
	@Column(name="professor_id")
	private int professorId;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public double getImplementationGrade() {
		return implementationGrade;
	}
	public void setImplementationGrade(double implementationGrade) {
		this.implementationGrade = implementationGrade;
	}
	public double getReportGrade() {
		return reportGrade;
	}
	public void setReportGrade(double reportGrade) {
		this.reportGrade = reportGrade;
	}
	public double getPresentationGrade() {
		return presentationGrade;
	}
	public void setPresentationGrade(double presentationGrade) {
		this.presentationGrade = presentationGrade;
	}
	
	
	public double getTotalGrade() {
		return 0.7*implementationGrade+
				0.15*reportGrade+
				0.15*presentationGrade;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public int getProfessorId() {
		return professorId;
	}
	public void setProfessorId(int professorId) {
		this.professorId = professorId;
	}
	
	public int getId() {
		return id;
	}
	
	
	public void getThesisInfo(Thesis thesis) {
		title = thesis.getTitle();
		professorId = thesis.getProfessorId();
	}
	
	public void copyGrades(AssignedThesis other) {
		this.implementationGrade = other.implementationGrade;
		this.presentationGrade = other.presentationGrade;
		this.reportGrade = other.reportGrade;
	}
}

package com.myy803.diplomas_mgt_app.service;

import java.util.List;

import com.myy803.diplomas_mgt_app.model.Application;
import com.myy803.diplomas_mgt_app.model.Student;
import com.myy803.diplomas_mgt_app.model.Thesis;

public interface StudentService{
	public List<Thesis> findAll();
	public void save(Student student);
	public Thesis findThesisById(int theId);
	public Student findById(int theId);
	public void saveThesis(Thesis thesis);
	public void saveApplication(Application application);
}

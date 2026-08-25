package com.myy803.diplomas_mgt_app.service;

import java.util.List;

import com.myy803.diplomas_mgt_app.model.AssignedThesis;
import com.myy803.diplomas_mgt_app.model.Professor;
import com.myy803.diplomas_mgt_app.model.Thesis;


public interface ProfessorService {

	public List<Thesis> findAll();
	public List<AssignedThesis> findAllAssigned();
	public void save(Thesis thesis);
	public void saveProf(Professor professor);
	public Thesis findById(int theId);
	public Professor findProfById(int theId);
	public AssignedThesis findAssignedById(int theId);
	public void deleteThesisById(int id);
	public void saveAssigned(AssignedThesis assigned);
}

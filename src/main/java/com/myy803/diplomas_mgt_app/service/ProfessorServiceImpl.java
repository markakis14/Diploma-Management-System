package com.myy803.diplomas_mgt_app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myy803.diplomas_mgt_app.dao.AssignedThesisDAO;
import com.myy803.diplomas_mgt_app.dao.ProfessorDAO;
import com.myy803.diplomas_mgt_app.dao.ThesisDAO;
import com.myy803.diplomas_mgt_app.model.AssignedThesis;
import com.myy803.diplomas_mgt_app.model.Professor;
import com.myy803.diplomas_mgt_app.model.Thesis;

@Service
public class ProfessorServiceImpl implements ProfessorService{
	
	@Autowired
	private ThesisDAO thesisRepository;
	
	@Autowired
	private ProfessorDAO professorDAO;
	
	@Autowired
	private AssignedThesisDAO assignedDAO;
	
	public ProfessorServiceImpl() {
		super();
	}
	
	@Autowired
	public ProfessorServiceImpl(ThesisDAO thesisRepository) {
		this.thesisRepository = thesisRepository;
	}
	


	@Override
	@Transactional
	public List<Thesis> findAll() {
		return thesisRepository.findAll();
	}

	@Override
	@Transactional
	public Thesis findById(int theId) {
		Thesis result = thesisRepository.findById(theId);
		if (result != null ) {
			return result;
		}
		else {
			// we didn't find the thesis
			throw new RuntimeException("Did not find thesis id - " + theId);
		}
	}
	
	@Override
	@Transactional
	public Professor findProfById(int theId) {
		Professor result = professorDAO.findById(theId);
		if (result != null) 
			return result;
		else
			throw new RuntimeException("Did not find professor id - " + theId);
	}
	
	@Override
	@Transactional
	public void save(Thesis thesis) {
		thesisRepository.save(thesis);
	}
	
	@Override
	@Transactional
	public void deleteThesisById(int theId) {
		thesisRepository.deleteById(theId);
	}

	@Override
	@Transactional
	public void saveProf(Professor professor) {
		professorDAO.save(professor);
	}
	
	@Override
	@Transactional
	public void saveAssigned(AssignedThesis assigned) {
		assignedDAO.save(assigned);
	}

	@Override
	@Transactional
	public List<AssignedThesis> findAllAssigned() {
		return assignedDAO.findAll();
	}

	@Override
	@Transactional
	public AssignedThesis findAssignedById(int theId) {
		AssignedThesis result = assignedDAO.findById(theId);
		if (result != null ) {
			return result;
		}
		else {
			throw new RuntimeException("Did not find assigned thesis id - " + theId);
		}
	}

}

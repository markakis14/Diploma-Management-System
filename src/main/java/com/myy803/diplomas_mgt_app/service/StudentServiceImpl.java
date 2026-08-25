package com.myy803.diplomas_mgt_app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myy803.diplomas_mgt_app.dao.ApplicationDAO;
import com.myy803.diplomas_mgt_app.dao.StudentDAO;
import com.myy803.diplomas_mgt_app.dao.ThesisDAO;
import com.myy803.diplomas_mgt_app.model.Application;
import com.myy803.diplomas_mgt_app.model.Student;
import com.myy803.diplomas_mgt_app.model.Thesis;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private ThesisDAO thesisRepository;
	
	@Autowired
	private StudentDAO studentDAO;
	
	@Autowired
	private ApplicationDAO applicationRepository;
	
	public StudentServiceImpl() {
		super();
	}
	
	@Override
	@Transactional
	public Thesis findThesisById(int theId) {
		Thesis result = thesisRepository.findById(theId);
		if (result != null ) {
			return result;
		}
		else {
			// we didn't find the employee
			throw new RuntimeException("Did not find theis id - " + theId);
		}
	}
	
	public StudentServiceImpl(ThesisDAO thesisRepository) {
		this.thesisRepository = thesisRepository;
	}

	@Override
	public List<Thesis> findAll() {
		return thesisRepository.findAll();
	}

	@Override
	public void save(Student student) {
		studentDAO.save(student);
	}

	@Override
	@Transactional
	public Student findById(int theId) {
		Student result = studentDAO.findById(theId);
		if (result != null)
			return result;
		else
			throw new RuntimeException("Did not fing the student id - "+ theId);
	}
	
	@Override
	@Transactional
	public void saveThesis(Thesis thesis) {
		thesisRepository.save(thesis);
	}

	@Override
	@Transactional
	public void saveApplication(Application application) {
		applicationRepository.save(application);
	}
}

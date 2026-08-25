package com.myy803.diplomas_mgt_app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myy803.diplomas_mgt_app.model.Professor;

@Repository
public interface ProfessorDAO extends JpaRepository<Professor, Integer> {
	public Professor findById(int theId);
}

package com.myy803.diplomas_mgt_app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myy803.diplomas_mgt_app.model.AssignedThesis;

@Repository
public interface AssignedThesisDAO extends JpaRepository<AssignedThesis, Integer> {
	public AssignedThesis findById(int theId);
}

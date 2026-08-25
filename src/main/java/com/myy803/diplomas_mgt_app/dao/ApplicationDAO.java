package com.myy803.diplomas_mgt_app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myy803.diplomas_mgt_app.model.Application;

@Repository
public interface ApplicationDAO extends JpaRepository<Application, Integer>{
	public Application findById(int theId);
}

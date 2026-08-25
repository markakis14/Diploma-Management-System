package com.myy803.diplomas_mgt_app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myy803.diplomas_mgt_app.model.Student;

public interface StudentDAO extends JpaRepository<Student, Integer> {
	public Student findById(int theId);
}

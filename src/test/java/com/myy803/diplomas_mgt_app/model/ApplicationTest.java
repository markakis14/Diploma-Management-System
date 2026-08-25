package com.myy803.diplomas_mgt_app.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class ApplicationTest {

	@Test
	void testStudentApplication() {
		Application application = new Application();
		Student student = new Student();
		student.setApplications(new ArrayList<Application>());
		application.setApplicant(student);
		
		application = new Application();
		application.setApplicant(student);
		
		application = new Application();
		application.setApplicant(student);
		
		assertEquals(3, student.getApplications().size());
	}
	
	@Test
	void testThesisApplication() {
		Application application = new Application();
		Thesis thesis = new Thesis();
		thesis.setApplications(new ArrayList<Application>());
		application.setThesis(thesis);
		
		application = new Application();
		application.setThesis(thesis);
		
		application = new Application();
		application.setThesis(thesis);
		
		assertEquals(3, thesis.getApplications().size());
	}
	
	@Test
	void testAllreadyApplied() {
		Student student = new Student();
		student.setApplications(new ArrayList<Application>());
		Thesis thesis1 = new Thesis();
		thesis1.setId(1);
		thesis1.setApplications(new ArrayList<Application>());
		Thesis thesis2 = new Thesis();
		thesis2.setId(2);
		thesis2.setApplications(new ArrayList<Application>());
		
		Application application = new Application();
		application.setApplicant(student);
		application.setThesis(thesis1);
		
		assertTrue(student.alreadyApplied(1));
		assertFalse(student.alreadyApplied(2));
		
	}

}

package com.myy803.diplomas_mgt_app.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AssignedThesisTest {

	@Test
	void testThesisInfo() {
		AssignedThesis assigned = new AssignedThesis();
		Thesis thesis = new Thesis();
		thesis.setTitle("Title");
		thesis.setProfessorId(2);
		assigned.getThesisInfo(thesis);
		
		assertEquals("Title", assigned.getTitle());
		assertEquals(2,assigned.getProfessorId());
	}
	
	@Test
	void testTotalGrade() {
		AssignedThesis assigned = new AssignedThesis();
		assigned.setImplementationGrade(10);
		assigned.setPresentationGrade(10);
		assigned.setReportGrade(10);
		
		assertEquals(10,assigned.getTotalGrade(), 0.05);
		
		assigned.setImplementationGrade(7.5);
		assigned.setPresentationGrade(6.3);
		assigned.setReportGrade(4.6);
		
		assertEquals(6.885,assigned.getTotalGrade(), 0.05);
		
		assigned.setImplementationGrade(5.3);
		assigned.setPresentationGrade(4.5);
		assigned.setReportGrade(3.8);
		
		assertEquals(4.955,assigned.getTotalGrade(),0.05);
	}

}

package com.myy803.diplomas_mgt_app.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class BestApplicantStrategyTest {
	private BestApplicantStrategy strategy;
	private BestApplicantStrategyFactory factory = new BestApplicantStrategyFactory();
	private static ArrayList<Application> applications = new ArrayList<>();
	
	@BeforeAll
	static void makeStudents() {
		Student student = new Student();
		student.setApplications(new ArrayList<Application>());
		student.setFullName("Student One");
		student.setCoursesRemaining(5);
		student.setCurrentAvgGrade(7.2);
		Application application = new Application();
		application.setApplicant(student);
		applications.add(application);
		
		student = new Student();
		student.setApplications(new ArrayList<Application>());
		student.setFullName("Student Two");
		student.setCoursesRemaining(12);
		student.setCurrentAvgGrade(6.5);
		application = new Application();
		application.setApplicant(student);
		applications.add(application);
		
		student = new Student();
		student.setApplications(new ArrayList<Application>());
		student.setFullName("Student Three");
		student.setCoursesRemaining(2);
		student.setCurrentAvgGrade(5.1);
		application = new Application();
		application.setApplicant(student);
		applications.add(application);
		
		student = new Student();
		student.setApplications(new ArrayList<Application>());
		student.setFullName("Best Grade");
		student.setCoursesRemaining(10);
		student.setCurrentAvgGrade(9.4);
		application = new Application();
		application.setApplicant(student);
		applications.add(application);
		
		student = new Student();
		student.setApplications(new ArrayList<Application>());
		student.setFullName("Fewest Courses");
		student.setCoursesRemaining(1);
		student.setCurrentAvgGrade(6.8);
		application = new Application();
		application.setApplicant(student);
		applications.add(application);
	}
	
	@Test
	void testFactory() {
		strategy = factory.createStrategy("random");
		assertEquals(RandomStrategy.class,strategy.getClass());
		strategy = factory.createStrategy("fewestCourses");
		assertEquals(FewestCoursesStrategy.class,strategy.getClass());
		strategy = factory.createStrategy("bestGrade");
		assertEquals(BestAvgGradeStrategy.class, strategy.getClass());
	}
	
	@Test
	void testRandomStrategy() {
		strategy = factory.createStrategy("random");
		assertEquals(Student.class,strategy.findBestApplicant(applications).getClass());
	}
	
	@Test
	void testBestGrade() {
		strategy = factory.createStrategy("bestGrade");
		assertEquals("Best Grade", strategy.findBestApplicant(applications).getFullName());
	}

	@Test
	void testFewestCourses() {
		strategy = factory.createStrategy("fewestCourses");
		assertEquals("Fewest Courses",strategy.findBestApplicant(applications).getFullName());
	}
}

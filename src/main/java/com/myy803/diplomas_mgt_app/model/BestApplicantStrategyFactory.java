package com.myy803.diplomas_mgt_app.model;

public class BestApplicantStrategyFactory {

	public BestApplicantStrategyFactory() {
		
	}
	
	public BestApplicantStrategy createStrategy(String strategy) {
		if (strategy.equals("fewestCourses"))
			return new FewestCoursesStrategy();
		else if (strategy.equals("random"))
			return new RandomStrategy();
		else
			return new BestAvgGradeStrategy();
	}
}

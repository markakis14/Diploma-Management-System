package com.myy803.diplomas_mgt_app.model;

public class FewestCoursesStrategy extends TemplateStrategyAlgorithm{

	@Override
	int compareApplications(Application first, Application second) {
		return Integer.compare(first.getApplicant().getCoursesRemaining(),
				second.getApplicant().getCoursesRemaining());
	}

}

package com.myy803.diplomas_mgt_app.model;

public class BestAvgGradeStrategy extends TemplateStrategyAlgorithm{

	@Override
	int compareApplications(Application first, Application second) {
		return Double.compare(second.getApplicant().getCurrentAvgGrade(),
				first.getApplicant().getCurrentAvgGrade());
	}

}

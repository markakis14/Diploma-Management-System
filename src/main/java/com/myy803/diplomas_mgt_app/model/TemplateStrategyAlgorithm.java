package com.myy803.diplomas_mgt_app.model;

import java.util.Collections;
import java.util.List;

public abstract class TemplateStrategyAlgorithm implements BestApplicantStrategy{
	public TemplateStrategyAlgorithm() {
	}
	
	public Student findBestApplicant(List<Application> applications) {
		Collections.sort(applications, (Application first, Application second) ->
		compareApplications(first,second));
		
		return applications.get(0).getApplicant();
	}
	
	abstract int compareApplications(Application first, Application second);
}

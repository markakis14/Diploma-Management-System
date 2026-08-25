package com.myy803.diplomas_mgt_app.model;

import java.util.List;

public interface BestApplicantStrategy {

	public Student findBestApplicant(List<Application> applications);
}

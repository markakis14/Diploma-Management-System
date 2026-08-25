package com.myy803.diplomas_mgt_app.model;

import java.util.Random;

public class RandomStrategy extends TemplateStrategyAlgorithm{
	private Random rand = new Random();
	@Override
	int compareApplications(Application first, Application second) {
		return Integer.compare(rand.nextInt(10), 4);
	}
}

package com.jvanhouteghem.springdemoannotations;

import com.jvanhouteghem.springdemoxml.Coach;

public class SwimCoach implements CoachAnnotations {

	private FortuneService fortuneService;
	
	public SwimCoach(FortuneService theFortuneService){
		fortuneService = theFortuneService;
	}
	
	@Override
	public String getDailyWorkout() {
		return "Swim 1000 meters as a warm up";
	}

	@Override
	public String getDailyFortune() {
		return fortuneService.getFortune();
	}

}

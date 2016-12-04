package com.jvanhouteghem.springdemoannotations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TennisCoach implements CoachAnnotations {
	
	private FortuneService fortuneService;
	
	// define a default constructor
	public TennisCoach(){
		System.out.println("TennisCoach : inside default constructor");
	}
	
	// define a setter method
	@Autowired
	public void doSomeCrazyStuff(FortuneService theFortuneService){
		System.out.println("TennisCoach : inside doSomeCrazyStuff() method");
		fortuneService = theFortuneService;
	}
	
	/*
	@Autowired
	public TennisCoach (FortuneService theFortuneService){
		fortuneService = theFortuneService;
	}*/

	@Override
	public String getDailyWorkout() {
		return "Practice your backhand volley";
	}

	@Override
	public String getDailyFortune() {
		return fortuneService.getFortune();
	}

}

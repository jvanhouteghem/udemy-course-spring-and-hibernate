package com.jvanhouteghem.springdemoannotations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.jvanhouteghem.springdemoxml.Coach;

@Configuration
@ComponentScan("com.jvanhouteghem.springdemoannotations")
public class SportConfig {
	
	// define bean for our sad fortune service
	@Bean
	public FortuneService sadFortuneService(){
		return new SadFortuneService();
	}
	
	// define bean for our swim coach AND inject dependency
	@Bean
	public CoachAnnotations swimCoach(){
		return new SwimCoach(sadFortuneService());
	}
	
}

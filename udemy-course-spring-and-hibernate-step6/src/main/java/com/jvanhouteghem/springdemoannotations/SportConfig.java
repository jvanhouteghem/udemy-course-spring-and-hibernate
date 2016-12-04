package com.jvanhouteghem.springdemoannotations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import com.jvanhouteghem.springdemoxml.Coach;

@Configuration
//@ComponentScan("com.jvanhouteghem.springdemoannotations")
@PropertySource("classpath:sport.properties")
public class SportConfig {
	
	// add support to resolve ${...} properties
	@Bean
	public static PropertySourcesPlaceholderConfigurer
		propertySourcesPlaceholderConfigurer(){
			return new PropertySourcesPlaceholderConfigurer();
	}
	
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

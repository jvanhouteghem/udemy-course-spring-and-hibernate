package com.jvanhouteghem.springdemoannotations;

import org.springframework.stereotype.Component;

@Component
public interface FortuneService {
	
	public String getFortune();

}

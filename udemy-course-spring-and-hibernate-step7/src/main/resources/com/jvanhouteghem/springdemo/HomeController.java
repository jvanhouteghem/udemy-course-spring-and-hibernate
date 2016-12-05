package com.jvanhouteghem.springdemo

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String showPage(){
		// Not the complete name of the page cause of the informations in the config file
		// <property name="prefix" value="/WEB-INF/view/" />
		// <property name="suffix" value=".jsp" />
		// --> /WEB-INF/view/ + NAME + .jsp
		return "main-menu";
	}
	
}

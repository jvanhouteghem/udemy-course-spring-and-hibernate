package com.jvanhouteghem.springmvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	// You can use any method name
	@RequestMapping("/")
	public String showPage(){
		// Return view name in WEB-INF/view
		return "main-menu";
	}
	
}

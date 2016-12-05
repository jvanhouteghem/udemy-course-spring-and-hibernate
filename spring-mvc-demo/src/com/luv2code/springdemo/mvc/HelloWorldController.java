package com.luv2code.springdemo.mvc;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/demo")
public class HelloWorldController {

	@RequestMapping("/hello")
	public String sayHello() {
		
		System.out.println(">>>> Inside of sayHello()");
				
		return "helloworld";
	}	
	
	@RequestMapping("/shouthello")
	public String sayFancyHello(HttpServletRequest request, Model model) {
		
		// read the request parameter
		String theName = request.getParameter("studentName");
		
		// convert to caps
		theName = theName.toUpperCase();
		
		// create the message
		String result = "YO! " + theName;
		
		// add message to the model
		model.addAttribute("message", result);
		
		// send to the "helloworld" view
		return "helloworld";		
	}

	@RequestMapping("/hellotwo")
	public String sayHelloTwo(@RequestParam("studentName") String theName, 
							  Model model) {
		
		// convert to caps
		theName = theName.toUpperCase();
		
		// create the message
		String result = "Hello Two Dude! " + theName;
		
		// add message to the model
		model.addAttribute("message", result);
		
		// send to the "helloworld" view
		return "helloworld";		
	}
	
	@RequestMapping("/showForm")
	public String showForm() {
		return "helloworld-form";
	}
		
}

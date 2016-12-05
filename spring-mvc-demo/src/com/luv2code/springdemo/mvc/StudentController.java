package com.luv2code.springdemo.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class StudentController {

	@RequestMapping("/showForm")
	public String showForm() {
		
		return "student-form";
	}
	
	@RequestMapping("/processForm")
	public String processForm(Student theStudent, Model theModel) {
		
		System.out.println("Student first name: " + theStudent.getFirstName());
		System.out.println("Student last name: " + theStudent.getLastName());
		
		// now let's update the student object
		// change case to all upper case
		//
		String capsFirstName = theStudent.getFirstName().toUpperCase();
		String capsLastName = theStudent.getLastName().toUpperCase();
		
		theStudent.setFirstName(capsFirstName);
		theStudent.setLastName(capsLastName);
		
		// add the student to the model
		theModel.addAttribute("myStudent", theStudent);
		
		return "student-confirmation";
	}
}

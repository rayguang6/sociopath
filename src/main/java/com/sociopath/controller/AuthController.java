package com.sociopath.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sociopath.model.entity.Student;
import com.sociopath.model.entity.Users;
import com.sociopath.service.StudentService;
import com.sociopath.service.UserService;

@Controller
public class AuthController {
	
	@Autowired
	private UserService userService;

	@Autowired
	StudentService studentService;

	
	@RequestMapping("/login")
	String admin() {
		return "login";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	ModelAndView register(ModelAndView modelAndView) {
		
		Users user = new Users();
		
		modelAndView.getModel().put("user", user);
		modelAndView.setViewName("register");
		return modelAndView;
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	ModelAndView register(ModelAndView modelAndView, @Valid Users user, BindingResult result) {
		modelAndView.setViewName("register");
		
		
		if(!result.hasErrors()) {
			userService.register(user);  //first, register the user
			
			//Straight Away Add the profile of the user
			Student student = new Student();
			student.setUsername(user.getUsername());
			student.generateTime();
			studentService.save(student);  //then use the username to create it's profile
			
			modelAndView.setViewName("redirect:/");
		}
		return modelAndView;
	}
	
	
}
package com.sociopath.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sociopath.model.entity.Student;
import com.sociopath.model.entity.Users;
import com.sociopath.service.StudentService;
import com.sociopath.service.UserService;

@Controller
public class GodActionController {
	
	@Autowired
	private UserService userService;

	@Autowired
	StudentService studentService;
	

	
	
	//testing all in one
		@RequestMapping(value="/GodAll")
		ModelAndView GodAll(ModelAndView modelAndView) {
			
			Users user = new Users();
			modelAndView.getModel().put("user", user);
			
			List<Student> students = studentService.getAllStudents();
			modelAndView.getModel().put("students", students);
			
			modelAndView.setViewName("app.GodAll");
			
			return modelAndView;
			
		}
		
		@RequestMapping(value="/GodAll", method=RequestMethod.POST)
		ModelAndView GodAll(ModelAndView modelAndView, @Valid Users user, BindingResult result) {
			modelAndView.setViewName("app.GodAll");
			
			if(!result.hasErrors()) {
				userService.register(user);  //first, register the user
				
				//Straight Away Add the profile of the user
				Student student = new Student();
				student.setUsername(user.getUsername());
				studentService.save(student);  
				
				modelAndView.setViewName("redirect:/GodAll");
			}
			return modelAndView;
		}
	
		
		@RequestMapping(value ="/godDeleteStudent", method=RequestMethod.GET)
		ModelAndView godDeleteStudent(ModelAndView modelAndView, @RequestParam(name="username") String username) {
			
			userService.godDeleteStudent(username);
			studentService.godDeleteStudent(username);
			modelAndView.setViewName("redirect:/");
			
			return modelAndView;
		}
		
	
}
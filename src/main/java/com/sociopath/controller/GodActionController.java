package com.sociopath.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

import com.sociopath.model.entity.ReputationRelation;
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

	// testing all in one
	//need this get method to show things on page
	@RequestMapping(value = "/GodAll")
	ModelAndView addStudent(ModelAndView modelAndView) {

		Users user = new Users();
		modelAndView.getModel().put("user", user);
		
		List<Student> students = studentService.getAllStudents();
		modelAndView.getModel().put("students", students);
		modelAndView.setViewName("app.GodAll");

		return modelAndView;
	}
	
	////Add Vertex
	//Create New User and StudentProfile
	@RequestMapping(value = "/GodAll", method = RequestMethod.POST)
	ModelAndView addStudent(ModelAndView modelAndView, @Valid Users user, BindingResult result) {
		modelAndView.setViewName("app.GodAll");

		if (!result.hasErrors()) {
			userService.register(user); // first, register the user

			// Straight Away Add the profile of the user
			Student student = new Student();
			student.setUsername(user.getUsername());
			studentService.save(student);

			modelAndView.setViewName("redirect:/GodAll");
		}
		return modelAndView;
	}

	//Delete The User and Profile
	@RequestMapping(value = "/godDeleteStudent", method = RequestMethod.GET)
	ModelAndView godDeleteStudent(ModelAndView modelAndView, @RequestParam(name = "username") String username) {

		userService.godDeleteStudent(username);
		studentService.godDeleteStudent(username);
		modelAndView.setViewName("redirect:/GodAll");

		return modelAndView;
	}

	@RequestMapping(value = "/addRelation")
	ModelAndView addRelation(ModelAndView modelAndView) {

		List<Student> students = studentService.getAllStudents();
		modelAndView.getModel().put("students", students);
		modelAndView.setViewName("app.addRelation");

		return modelAndView;
	}
	
	////Add Edge
	@RequestMapping(value = "/addRelation", method = RequestMethod.POST)
	ModelAndView addRelation(ModelAndView modelAndView, @RequestParam(name = "student1") String source,@RequestParam(name = "student2") String destination,@RequestParam(name = "rep_point") int rep_point) {
		
		studentService.addEdge(source,destination,rep_point,false);
		modelAndView.setViewName("redirect:/addRelation");
		return modelAndView;
	}
	
	//show form
	@RequestMapping(value = "/testCreate")
	ModelAndView testCreate(ModelAndView modelAndView) {

		List<Student> students = studentService.getAllStudents();
		modelAndView.getModel().put("students", students);
		modelAndView.setViewName("app.testCreate");
		return modelAndView;
	}
	
//	//Create Using The Spring Data Mapping
//	//好像是因为neo4j 的ogm的问题   只能弄single direction...
//	@RequestMapping(value = "/testCreate", method = RequestMethod.POST)
//	ModelAndView testCreate(ModelAndView modelAndView, @RequestParam(name = "student1") String source,@RequestParam(name = "student2") String destination, @RequestParam(name = "rep_point") int rep_point) {
//		
//		studentService.testCreate(source,destination,rep_point);
//		modelAndView.setViewName("redirect:/testCreate");
//		
//		return modelAndView;
//	}

	
	@RequestMapping(value = "/testForm")
	ModelAndView testForm(ModelAndView modelAndView) {

		modelAndView.setViewName("app.testForm");

		return modelAndView;
	}
	
	@RequestMapping(value = "/testForm", method = RequestMethod.POST)
	ModelAndView testForm(ModelAndView modelAndView, @RequestParam(name = "source") String source, @RequestParam(name = "destination") String destination) {
		
		int rep = studentService.getRep(source, destination);
		
		System.out.println("######################################");
		System.out.println("The Rep Of?   =  "+rep);
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		modelAndView.setViewName("redirect:/testForm");
		return modelAndView;
	}
	
	@RequestMapping(value = "/displayReputation")
	ModelAndView displayReputation(ModelAndView mav) {

		List<Student> students = studentService.getAllStudents();
				
		mav.getModel().put("students", students);
		mav.setViewName("app.displayReputation");

		return mav;
	}
	
	
}
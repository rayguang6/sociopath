package com.sociopath.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sociopath.model.entity.StatusUpdate;
import com.sociopath.model.entity.Student;
import com.sociopath.service.StudentService;
import com.sociopath.service.UserService;

@Controller
public class StudentController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	StudentService studentService;
	
	private String getUsername() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		//return userService.getUserName(username);   如果要找user  就要用这个， 但是我们assume user 和 studProfile的名字一样
		
		return auth.getName();
	}

	@RequestMapping("/studentprofile")
	ModelAndView showStudentProfile(ModelAndView modelAndView) {
		
		String username = getUsername();  //call the above method to get username of current user
		Student student = studentService.getStudent(username); //Retrieve student profile by calling the studServ
		
		//if the user dont have a student profile yet, then create new student
		if(student==null) {
			student = new Student();
			student.setUsername(username);//assign the username of user to the Student Profile Class
			studentService.save(student);  //Save the studProfile into DB
		}
		
		modelAndView.getModel().put("student", student);
				
		modelAndView.setViewName("app.studentprofile");
		
		return modelAndView;
	}
}

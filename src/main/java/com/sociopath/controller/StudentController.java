package com.sociopath.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sociopath.model.entity.StatusUpdate;
import com.sociopath.model.entity.Student;
import com.sociopath.model.entity.Users;
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
	
	
	private Users getUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();

		return userService.get(username);
	}
	
	

//	@RequestMapping("/profile")
//	ModelAndView showStudentProfile(ModelAndView modelAndView) {
//		
//		String username = getUsername();  //call the above method to get username of current user
//		Student student = studentService.getStudent(username); //Retrieve student profile by calling the studServ
//		
//		//if the user dont have a student profile yet, then create new student
//		if(student==null) {
//			student = new Student();
//			student.setUsername(username);//assign the username of user to the Student Profile Class
//			studentService.save(student);  //Save the studProfile into DB
//		}
//		
//		modelAndView.getModel().put("student", student);
//				
//		modelAndView.setViewName("profile");
//		
//		return modelAndView;
//	}
	
	
	
	
	/**/
	
	
	//通过username  拿到student  然后显示那个student的profile
	private ModelAndView showProfile(Users user) {
		
		ModelAndView modelAndView = new ModelAndView();
		
		if(user==null) {
			modelAndView.setViewName("redirect:/");
			return modelAndView;
		}
		
		Student student = studentService.getStudent(user.getUsername());
		
		
		modelAndView.getModel().put("userId", user.getUsername());
		modelAndView.getModel().put("student", student);
		
		modelAndView.setViewName("profile");
		
		return modelAndView;
	}
	
	
	
	
	/**/

	@RequestMapping(value="/profile")
	public ModelAndView showProfile() {
		
		Users user = getUser();
		
		String username = getUsername(); //retrieve this logged in account's username
		
		ModelAndView modelAndView = showProfile(user);		
		modelAndView.getModel().put("loggedIn", username);
		return modelAndView;
	}
	
	@RequestMapping(value = "/profile/{username}")
	public ModelAndView showProfile(@PathVariable("username") String username) {

		Users user = userService.get(username);

		ModelAndView modelAndView = showProfile(user);
		
		return modelAndView;
	}
	
	
	//display Stranger of this account for EVENT 1
	@RequestMapping(value = "/FindStranger")
	ModelAndView FindStranger(ModelAndView mav) {
		
		String loggedIn = getUsername();
		
		List<Student> myStrangers = studentService.getStrangersByUsername(loggedIn);
		mav.getModel().put("loggedIn", loggedIn);
		mav.getModel().put("myStrangers", myStrangers);
		mav.setViewName("/FindStranger");
		
		return mav;
	}
	
	
	@RequestMapping(value = "/FindStranger/{username}")
	public ModelAndView askStranger(ModelAndView mav, @PathVariable("username") String username) {

		String student = getUsername();

		String teacher = username;
		
		mav.getModel().put("student", student);
		mav.getModel().put("teacher", teacher);
		mav.setViewName("askStranger");
		return mav;
	}
	
	@RequestMapping(value = "/askStranger", method = RequestMethod.POST)
	ModelAndView askStranger(ModelAndView modelAndView, @RequestParam(name = "mentee") String mentee,@RequestParam(name = "mentor") String mentor,@RequestParam(name = "rep_point") int rep_point) {
		
		Student stud = studentService.getStudent(mentee);
		
		List befores = stud.getReputationList();
		
		List<String> results = studentService.teachStranger(mentee,mentor,rep_point);
		
		List afters = stud.getReputationList();
		
		modelAndView.getModel().put("results", results);
		modelAndView.getModel().put("mentee", mentee);
		modelAndView.getModel().put("mentor", mentor);
		
		modelAndView.setViewName("redirect:/dashboard");
		return modelAndView;
	}
	
	
	/////////////////////////////
	//display Friends of this account for EVENT 2
	@RequestMapping(value = "/MyFriends")
	ModelAndView MyFriends(ModelAndView mav) {
		
		String loggedIn = getUsername();
		
		List<Student> myFriends = studentService.getFriendsByUsername(loggedIn);
		
		mav.getModel().put("loggedIn", loggedIn);
		mav.getModel().put("myFriends", myFriends);
		mav.setViewName("/MyFriends");
		
		return mav;
	}
	
	
	
	//Chit Chat
	@RequestMapping(value = "/MyFriends/{username}")
	ModelAndView ChitChat(ModelAndView mav, @PathVariable("username") String username) {
		
		String me = getUsername();
		String thisFriend = username;
		
		mav.getModel().put("me", me);
		mav.getModel().put("thisFriend", thisFriend);
		
		List<Student> students = studentService.getFriendsByUsername(me);//get my friends so the 3rd person must be myfriend

		mav.getModel().put("students", students);
		mav.setViewName("/ChitChat");

		return mav;
	}

	
	@RequestMapping(value = "/ChitChat", method = RequestMethod.POST)
	ModelAndView Event2(ModelAndView modelAndView, @RequestParam(name = "s1") String s1,
			@RequestParam(name = "s2") String s2, @RequestParam(name = "s3") String s3,
			@RequestParam(name = "good") boolean good) {

		List<String> results = studentService.chitchat(s1, s2, s3, good);
		modelAndView.getModel().put("results", results);
		modelAndView.getModel().put("s1", s1);
		modelAndView.getModel().put("s2", s2);
		modelAndView.getModel().put("s3", s3);
		
		modelAndView.setViewName("/dashboard");
		return modelAndView;
	}
	
	@RequestMapping(value = "/ArrangeBook")
	ModelAndView ArrangeBook(ModelAndView modelAndView) {

		List<Student> students = studentService.getAllStudents();
		modelAndView.getModel().put("students", students);
		modelAndView.setViewName("ArrangeBook");

		return modelAndView;
	}
	
	//@RequestParam(name = "books") String books
	@RequestMapping(value = "/ArrangeBook", method = RequestMethod.POST)
	ModelAndView ArrangeBook( HttpServletRequest request,HttpServletResponse response) {

		String books = request.getParameter("books");
		
		 Map<String, Integer> bookResults = studentService.arrangeBook(books);

		ModelAndView mv = new ModelAndView();
		
//		mv.getModel().put("books", books);
		mv.addObject("books",books);
		
		mv.addObject("bookResults", bookResults);
		
		mv.setViewName("/arrangeBookResult");
		return mv;
	}
	
	
}

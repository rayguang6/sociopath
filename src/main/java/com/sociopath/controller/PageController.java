package com.sociopath.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sociopath.model.entity.Post;
import com.sociopath.model.entity.StatusUpdate;
import com.sociopath.model.entity.Student;
import com.sociopath.model.entity.Users;
import com.sociopath.service.StatusUpdateService;
import com.sociopath.service.StudentService;
import com.sociopath.service.UserService;


@Controller
public class PageController {

	
	@Value("${message.error.forbidden}")
	private String accessDeniedMessage;
	

	@Autowired
	UserService userService;
	
	@Autowired
	StudentService studentService;
	
		
	private String getUsername() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				
		return auth.getName();
	}
	
	
	private Users getUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();

		return userService.get(username);
	}
	

	
	
	@RequestMapping("/")
	ModelAndView home(ModelAndView modelAndView) {
		
		//StatusUpdate statusUpdate = statusUpdateService.getLatest();
		
		//modelAndView.getModel().put("statusUpdate", statusUpdate);
		
		String loggedIn = getUsername();
		
		modelAndView.getModel().put("loggedIn", loggedIn);
		modelAndView.getModel().put("username", loggedIn);
		
		List<Post> posts = studentService.getPosts();
		modelAndView.getModel().put("posts", posts);
		modelAndView.setViewName("/dashboard");
		
		return modelAndView;
	}
	
	@RequestMapping("/dashboard")
	ModelAndView dashboard(ModelAndView modelAndView) {
		
		//StatusUpdate statusUpdate = statusUpdateService.getLatest();
		
		//modelAndView.getModel().put("statusUpdate", statusUpdate);
		String loggedIn = getUsername();
		modelAndView.getModel().put("loggedIn", loggedIn);

		modelAndView.getModel().put("username", loggedIn);
		
		List<Post> posts = studentService.getPosts();
		modelAndView.getModel().put("posts", posts);
		
		modelAndView.setViewName("/dashboard");
		
		return modelAndView;
	}
	
	
	//handle access denied
	@RequestMapping("/403")
	ModelAndView accessDenied(ModelAndView modelAndView) {

		modelAndView.getModel().put("message", accessDeniedMessage);
		modelAndView.setViewName("message");
		return modelAndView;
	}
	
	@RequestMapping(value = "/leaderboard")
	ModelAndView reputationBoard(ModelAndView mav) {

		String loggedIn = getUsername();
		mav.getModel().put("loggedIn", loggedIn);
		
		List<Student> students = studentService.getAllStudents();

		Map<String, Integer> reputationBoards = studentService.reputationBoard();

		Map<Student, List<Student>> friendMaps = studentService.getAllFriends();

		mav.getModel().put("students", students);
		mav.getModel().put("reputationBoards", reputationBoards);

		mav.getModel().put("friendMaps", friendMaps);

		mav.setViewName("/leaderboard");

		return mav;
	}
	
	@RequestMapping("/Lunch")
	ModelAndView Lunch(ModelAndView modelAndView) {
		
		modelAndView.setViewName("/Lunch");
		
		return modelAndView;
	}
	
	
}

package com.sociopath.controller;

import java.util.ArrayList;
import java.util.Hashtable;
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

import com.sociopath.model.dto.FriendResult;
import com.sociopath.model.entity.CrushGraph;
import com.sociopath.model.entity.FriendshipList;
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
	// need this get method to show things on page
	@RequestMapping(value = "/godDashboard")
	ModelAndView addStudent(ModelAndView modelAndView) {

		Users user = new Users();
		modelAndView.getModel().put("user", user);

		List<Student> students = studentService.getAllStudents();
		modelAndView.getModel().put("students", students);
		modelAndView.setViewName("/god/godDashboard");

		return modelAndView;
	}

	//// Add Vertex
	// Create New User and StudentProfile
	@RequestMapping(value = "/godDashboard", method = RequestMethod.POST)
	ModelAndView addStudent(ModelAndView modelAndView, @Valid Users user, BindingResult result) {
		modelAndView.setViewName("/god/godDashboard");

		if (!result.hasErrors()) {
			userService.register(user); // first, register the user

			// Straight Away Add the profile of the user
			Student student = new Student();
			student.setUsername(user.getUsername());
			student.generateTime();
			studentService.save(student);

			modelAndView.setViewName("redirect:/godDashboard");
		}
		return modelAndView;
	}

	// Delete The User and Profile
	@RequestMapping(value = "/godDeleteStudent", method = RequestMethod.GET)
	ModelAndView godDeleteStudent(ModelAndView modelAndView, @RequestParam(name = "username") String username) {

		userService.deleteStudent(username);
		studentService.deleteStudent(username);
		modelAndView.setViewName("redirect:/godDashboard");

		return modelAndView;
	}

	@RequestMapping(value = "/godCreateRep")
	ModelAndView addRelation(ModelAndView modelAndView) {

		List<Student> students = studentService.getAllStudents();
		modelAndView.getModel().put("students", students);
		modelAndView.setViewName("god/godCreateRep");

		return modelAndView;
	}

	//// Add Edge
	@RequestMapping(value = "/godCreateRep", method = RequestMethod.POST)
	ModelAndView godCreateRep(ModelAndView modelAndView, @RequestParam(name = "student1") String source,
			@RequestParam(name = "student2") String destination, @RequestParam(name = "rep_point") int rep_point) {

		studentService.createRep(source, destination, rep_point);
		modelAndView.setViewName("redirect:/godCreateRep");
		return modelAndView;
	}

	@RequestMapping(value = "/godUpdateRep", method = RequestMethod.POST)
	ModelAndView godUpdateRep(ModelAndView modelAndView, @RequestParam(name = "student1") String source,
			@RequestParam(name = "student2") String destination, @RequestParam(name = "rep_point") int rep_point) {

		studentService.updateRep(source, destination, rep_point);
		modelAndView.setViewName("redirect:/godCreateRep");
		return modelAndView;
	}

	// delete reputation
	@RequestMapping(value = "/godDeleteRep", method = RequestMethod.POST)
	ModelAndView godCreateRep(ModelAndView modelAndView, @RequestParam(name = "student1") String source,
			@RequestParam(name = "student2") String destination) {

		studentService.deleteRep(source, destination);
		modelAndView.setViewName("redirect:/godCreateRep");
		return modelAndView;
	}

	// delete both rep
	@RequestMapping(value = "/godDeleteBothRep", method = RequestMethod.POST)
	ModelAndView godDeleteBothRep(ModelAndView modelAndView, @RequestParam(name = "student1") String source,
			@RequestParam(name = "student2") String destination) {

		studentService.deleteBothRep(source, destination);
		modelAndView.setViewName("redirect:/godCreateRep");
		return modelAndView;
	}

	// delete both rep
	@RequestMapping(value = "/godDeleteAllRep", method = RequestMethod.POST)
	ModelAndView godDeleteAllRep(ModelAndView modelAndView) {

		studentService.deleteAllRep();
		modelAndView.setViewName("redirect:/godCreateRep");
		return modelAndView;
	}

	// Reset Default Rep in Question
	@RequestMapping(value = "/createBasicRep", method = RequestMethod.POST)
	ModelAndView createBasicRep(ModelAndView modelAndView) {

		studentService.deleteAllRep();
		studentService.createRep("1", "7", 4);
		studentService.createRep("7", "1", 3);
		studentService.createRep("1", "2", 5);
		studentService.createRep("2", "1", 8);
		studentService.createRep("2", "6", 9);
		studentService.createRep("6", "2", 7);
		studentService.createRep("2", "5", 6);
		studentService.createRep("5", "2", 2);
		studentService.createRep("2", "3", 5);
		studentService.createRep("3", "2", 4);
		
		studentService.createRep("4", "8", 7);
		studentService.createRep("8", "4", 10);
		studentService.createRep("4", "10", 7);
		studentService.createRep("10", "4", 7);
		studentService.createRep("10", "9", 6);
		studentService.createRep("9", "10", 5);
		
		modelAndView.setViewName("redirect:/godCreateRep");
		return modelAndView;
	}

	// show form
	@RequestMapping(value = "/godCreateFriend")
	ModelAndView godCreateFriend(ModelAndView modelAndView) {

		List<Student> students = studentService.getAllStudents();
		modelAndView.getModel().put("students", students);
		modelAndView.setViewName("god/godCreateFriend");
		return modelAndView;
	}

	// Create Friend
	@RequestMapping(value = "/godCreateFriend", method = RequestMethod.POST)
	ModelAndView godCreateFriend(ModelAndView modelAndView, @RequestParam(name = "student1") String source,
			@RequestParam(name = "student2") String destination) {

		studentService.createFriend(source, destination);
		modelAndView.setViewName("redirect:/godCreateFriend");

		return modelAndView;
	}

	// delete both rep
	@RequestMapping(value = "/godDeleteBothFriend", method = RequestMethod.POST)
	ModelAndView godDeleteBothFriend(ModelAndView modelAndView, @RequestParam(name = "student1") String source,
			@RequestParam(name = "student2") String destination) {

		studentService.deleteBothFriend(source, destination);
		modelAndView.setViewName("redirect:/godCreateFriend");
		return modelAndView;
	}

	// delete both rep
	@RequestMapping(value = "/godDeleteAllFriend", method = RequestMethod.POST)
	ModelAndView godDeleteAllFriend(ModelAndView modelAndView) {

		studentService.deleteAllFriend();
		modelAndView.setViewName("redirect:/godCreateFriend");
		return modelAndView;
	}
	
	// Reset Default Friends in Question
		@RequestMapping(value = "/createBasicFriend", method = RequestMethod.POST)
		ModelAndView createBasicFriend(ModelAndView modelAndView) {

		studentService.deleteAllFriend();
		
		studentService.createFriend("1", "7");
		studentService.createFriend("1", "2");
		studentService.createFriend("3", "2");
		studentService.createFriend("5", "2");
		studentService.createFriend("6", "2");
		studentService.createFriend("4", "8");
		studentService.createFriend("4", "10");
		studentService.createFriend("10", "9");

		modelAndView.setViewName("redirect:/godCreateFriend");
		return modelAndView;
		}

	@RequestMapping(value = "/testForm")
	ModelAndView testForm(ModelAndView modelAndView) {
		modelAndView.setViewName("god/testForm");
		return modelAndView;
	}

	@RequestMapping(value = "/testForm", method = RequestMethod.POST)
	ModelAndView testForm(ModelAndView modelAndView, @RequestParam(name = "source") String source,
			@RequestParam(name = "destination") String destination) {

		studentService.increaseIndeg(destination);
		studentService.increaseOutdeg(source);

		System.out.println("######################################");
		System.out.println("Is " + source + " AND " + destination + " Friend?   =  ");
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		modelAndView.setViewName("redirect:/testForm");
		return modelAndView;
	}

	@RequestMapping(value = "/displayReputation")
	ModelAndView displayReputation(ModelAndView mav) {

		List<Student> students = studentService.getAllStudents();
		List<Student> friends = studentService.getFriendsByUsername("CCF");

		mav.getModel().put("students", students);
		mav.getModel().put("friends", friends);
		mav.setViewName("/god/displayReputation");

		return mav;
	}

	@RequestMapping(value = "/displayFriend")
	ModelAndView displayFriend(ModelAndView mav) {

		List<Student> students = studentService.getAllStudents();

		String username = "CCF";

		List<Student> myfriends = studentService.getFriendsByUsername(username); // get this user's friend

//		Map<Student,List<Student>> friends = studentService.getAllFriends(); 

		Map<String, List<String>> friends = studentService.getAllFriendsUsername();

		Map<Student, List<Student>> friendMaps = studentService.getAllFriends();

		mav.getModel().put("students", students);
		mav.getModel().put("myfriends", myfriends);
		mav.getModel().put("friends", friends);

		mav.getModel().put("friendMaps", friendMaps);

		mav.setViewName("/god/displayFriend");

		return mav;
	}

//	@RequestMapping(value = "/leaderboard")
//	ModelAndView reputationBoard(ModelAndView mav) {
//
//		List<Student> students = studentService.getAllStudents();
//		
//		Map<String, Integer> reputationBoards = studentService.reputationBoard();
//		
//		Map<Student, List<Student>> friendMaps = studentService.getAllFriends();
//		
//		mav.getModel().put("students", students);
//		mav.getModel().put("reputationBoards", reputationBoards);
//		
//		mav.getModel().put("friendMaps", friendMaps);
//		
//		mav.setViewName("/leaderboard");
//
//		return mav;
//	}

	///////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////// Sociopath Assignment
	/////////////////////////////////////////////////////////////////////////////////////////////// Event////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////

	// #############################################################################################
	// EVENT 1 --> Teaching Strangers
	// ##############################################################################################
	@RequestMapping(value = "/event1")
	ModelAndView Event1(ModelAndView modelAndView) {

		List<Student> students = studentService.getAllStudents();
		modelAndView.getModel().put("students", students);
		modelAndView.setViewName("/god/event1");

		return modelAndView;
	}

	@RequestMapping(value = "/event1", method = RequestMethod.POST)
	ModelAndView Event1(ModelAndView modelAndView, @RequestParam(name = "mentee") String mentee,
			@RequestParam(name = "mentor") String mentor, @RequestParam(name = "rep_point") int rep_point) {

		Student stud = studentService.getStudent(mentee);

		List befores = stud.getReputationList();

		List<String> results = studentService.teachStranger(mentee, mentor, rep_point);

		List afters = stud.getReputationList();

		modelAndView.getModel().put("results", results);
		modelAndView.getModel().put("mentee", mentee);
		modelAndView.getModel().put("mentor", mentor);

		modelAndView.setViewName("/god/resultEvent1");
		return modelAndView;
	}

	// #############################################################################################
	// EVENT 2 --> CHIT-CHAT
	// ##############################################################################################
	@RequestMapping(value = "/event2")
	ModelAndView Event2(ModelAndView modelAndView) {

		List<Student> students = studentService.getAllStudents();
		modelAndView.getModel().put("students", students);
		modelAndView.setViewName("/god/event2");

		return modelAndView;
	}

	//// Add Edge
	@RequestMapping(value = "/event2", method = RequestMethod.POST)
	ModelAndView Event2(ModelAndView modelAndView, @RequestParam(name = "s1") String s1,
			@RequestParam(name = "s2") String s2, @RequestParam(name = "s3") String s3,
			@RequestParam(name = "good") boolean good) {

		List<String> results = studentService.chitchat(s1, s2, s3, good);
		modelAndView.getModel().put("results", results);
		modelAndView.getModel().put("s1", s1);
		modelAndView.getModel().put("s2", s2);
		modelAndView.getModel().put("s3", s3);
		modelAndView.setViewName("/god/resultEvent2");
		return modelAndView;
	}

	// #############################################################################################
	// EVENT 3 --> Road To The Glory
	// ##############################################################################################

	@RequestMapping(value = "/event3")
	ModelAndView Event3(ModelAndView modelAndView) {

		List<Student> students = studentService.getAllStudents();
		modelAndView.getModel().put("students", students);
		modelAndView.setViewName("god/event3");

		return modelAndView;
	}

	@RequestMapping(value = "/event3", method = RequestMethod.POST)
	ModelAndView Event3(ModelAndView modelAndView, @RequestParam(name = "s1") String[] s1,
			@RequestParam(name = "s2") String s2, @RequestParam(name = "day") int day) {

		ArrayList<String> lunchLists = studentService.haveLunch(s1, s2, day);

		modelAndView.getModel().put("lunchLists", lunchLists);
		modelAndView.setViewName("god/resultEvent3");
		return modelAndView;
	}

	//// EVENT 4
	@RequestMapping(value = "/event4")
	ModelAndView Event4(ModelAndView modelAndView) {

		List<Student> students = studentService.getAllStudents();
		modelAndView.getModel().put("students", students);
		modelAndView.setViewName("god/event4");

		return modelAndView;
	}

	@RequestMapping(value = "/event4", method = RequestMethod.POST)
	ModelAndView Event4(ModelAndView modelAndView, @RequestParam(name = "books") String books) {

		List<String> bookResults = studentService.Event4(books);

		modelAndView.getModel().put("books", books);

		modelAndView.getModel().put("bookResults", bookResults);

		modelAndView.setViewName("/god/resultEvent4");
		return modelAndView;
	}

	/////////////////////////
	/// EVENT 5
	@RequestMapping(value = "/event5")
	ModelAndView Event5(ModelAndView modelAndView) {

		List<Student> students = studentService.getAllStudents();
		modelAndView.getModel().put("students", students);
		modelAndView.setViewName("god/event5");

		return modelAndView;
	}

	@RequestMapping(value = "/event5", method = RequestMethod.POST)
	ModelAndView Event5(ModelAndView modelAndView, @RequestParam(name = "crush") int crush,
			@RequestParam(name = "rumor") int rumor, @RequestParam(name = "choice") int choice) {

		Map<Student, List<Student>> friendMaps = studentService.getAllFriends();
		List<Student> students = studentService.getAllStudents();

		ArrayList<String> results = studentService.event5(rumor, crush, choice);

		modelAndView.getModel().put("crush", crush);
		modelAndView.getModel().put("rumor", rumor);
		modelAndView.getModel().put("choice", choice);
		
		modelAndView.getModel().put("students", students);
		modelAndView.getModel().put("friendMaps", friendMaps);
		modelAndView.getModel().put("results", results);

		modelAndView.setViewName("god/resultEvent5");
		return modelAndView;
	}

	@RequestMapping(value = "/resultEvent5")
	ModelAndView resultEvent5(ModelAndView modelAndView) {

		Map<Student, List<Student>> friendMaps = studentService.getAllFriends();
		List<Student> students = studentService.getAllStudents();
		modelAndView.getModel().put("students", students);
		modelAndView.getModel().put("friendMaps", friendMaps);

		modelAndView.setViewName("god/resultEvent5");

		return modelAndView;
	}

	
	//////////////////////////////////////
	//// EVENT 6
	@RequestMapping(value = "/preEvent6")
	ModelAndView preEvent6(ModelAndView modelAndView) {

		List<Student> students = studentService.getAllStudents();
		modelAndView.getModel().put("students", students);
		modelAndView.setViewName("god/preEvent6");

		return modelAndView;
	}

	@RequestMapping(value = "/preEvent6", method = RequestMethod.POST)
	ModelAndView preEvent6(ModelAndView modelAndView, @RequestParam(name = "numOfRelation") int numOfRelation) {

		modelAndView.getModel().put("numOfRelation", numOfRelation);
		
		modelAndView.setViewName("god/event6");
		return modelAndView;
	}

	@RequestMapping(value = "/event6", method = RequestMethod.POST)
	ModelAndView Event6(ModelAndView modelAndView, @RequestParam(name = "numOfRelation") int numOfRelation,
			@RequestParam(name = "s1") int[] s1, @RequestParam(name = "s2") int[] s2) {

		modelAndView.getModel().put("numOfRelation", numOfRelation);
		modelAndView.getModel().put("s1", s1);
		modelAndView.getModel().put("s2", s2);

		//make a small checking here, if user key in studentName that's > numofRelation   then return errorMsg
		
		for(int i=0; i<s1.length; i++) {
			if(s1[i]>numOfRelation || s2[i]>numOfRelation) {
				String errorMessage = "Can't perform this action. You entered student that's greater than "+numOfRelation+".";
				modelAndView.getModel().put("friendshipLists", errorMessage);
				modelAndView.setViewName("god/resultEvent6");
				return modelAndView;
			}
			
	
		}
		
		ArrayList<FriendshipList> friendshipLists = studentService.event6(s1, s2, numOfRelation);

		modelAndView.getModel().put("friendshipLists", friendshipLists);

		modelAndView.setViewName("god/resultEvent6");
		return modelAndView;
	}

	@RequestMapping(value = "/resultEvent6")
	ModelAndView resultEvent6(ModelAndView modelAndView) {

		List<Student> students = studentService.getAllStudents();
		modelAndView.getModel().put("students", students);
		modelAndView.setViewName("god/resultEvent6");

		return modelAndView;
	}
	
	
	@RequestMapping("/index")
	ModelAndView index(ModelAndView modelAndView) {
		
		modelAndView.setViewName("/index");
		
		return modelAndView;
	}
	
	
	/*###################################################################################################*/
	/*##########################                 SOME EXTRA FEATURES             ########################*/
	/*###################################################################################################*/
	
	
	//View Frenemy
	@RequestMapping(value = "/displayFriend/{username}")
	ModelAndView ChitChat(ModelAndView mav, @PathVariable("username") String username) {
		//username is the one we view his profile
		mav.getModel().put("username", username);
			
		Student student = studentService.getStudent(username);
		Map<String, Integer> frenemies = studentService.getFrenemy(username);
		Map<String, Integer> realFriends = studentService.getRealFriend(username);
		Map<String, Integer> myFriendMaps = studentService.getFriendMap(username);
		
		mav.getModel().put("student", student);
		mav.getModel().put("frenemies", frenemies);
		mav.getModel().put("realFriends", realFriends);
		mav.getModel().put("myFriendMaps", myFriendMaps);
		mav.setViewName("/god/godViewProfile");

		return mav;
	}
	
	
	
	

}
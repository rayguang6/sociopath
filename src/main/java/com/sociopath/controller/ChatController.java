package com.sociopath.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sociopath.model.dto.ChatRequest;
import com.sociopath.model.dto.SimpleMessage;
import com.sociopath.model.entity.Student;
//import com.sociopath.model.entity.Message;
import com.sociopath.model.entity.Users;
import com.sociopath.service.StudentService;
//import com.sociopath.service.MessageService;
import com.sociopath.service.UserService;



@Controller
public class ChatController {

	@Autowired
	private SimpMessagingTemplate simpleMessagingTemplate;

	@Autowired
	private Util util;

	@Autowired
	private UserService userService;
	
	@Autowired
	private StudentService studentService;

//	@Autowired
//	private MessageService messageService;

//	@RequestMapping("/markread")
//	String markRead(@RequestParam("f") long userId, @RequestParam("m") long messageId) {
//		
//		Optional<Message> messageOpt = messageService.get(messageId);
//		
//		if(messageOpt.isPresent()) {
//			Message message = messageOpt.get();
//			
//			message.setRead(true);
//			messageService.save(message);
//		}
//		
//		return "redirect:/chatview/" + userId;
//	}

//	@RequestMapping("/messages")
//	ModelAndView expiredToken(ModelAndView modelAndView, @RequestParam("p") int pageNumber) {
//
//		Users user = util.getUser();
//		Page<SimpleMessage> messages = messageService.fetchMessageListPage(user.getId(), pageNumber);
//
//		modelAndView.getModel().put("messageList", messages);
//		modelAndView.getModel().put("pageNumber", pageNumber);
//		modelAndView.getModel().put("userId", user.getId());
//
//		modelAndView.setViewName("checkmessages");
//		return modelAndView;
//	}

//	@RequestMapping(value = "/conversation/{otherUserId}", method = RequestMethod.POST, produces = "application/json")
//	@ResponseBody
//	List<SimpleMessage> fetchConversation(@PathVariable("otherUserId") Long otherUserId,
//			@RequestBody ChatRequest request) {
//
//		Users thisUser = util.getUser();
//
//		List<SimpleMessage> list = messageService.fetchConversation(thisUser.getId(), otherUserId, request.getPage());
//
//		return list;
//	}

	@RequestMapping("/chatview/{chatWithUserID}")
	ModelAndView chatView(ModelAndView modelAndView, @PathVariable String chatWithUserID) {

		Users thisUser = util.getUser();
		String chatWithUserName = chatWithUserID;
		
		String me = thisUser.getUsername();
		
		List<Student> students = studentService.getFriendsByUsername(me);//get my friends so the 3rd person must be myfriend

		modelAndView.getModel().put("students", students);

		modelAndView.getModel().put("loggedIn", me);
		modelAndView.setViewName("chatview");
		modelAndView.getModel().put("thisUserID", me);
		modelAndView.getModel().put("me", me);
		modelAndView.getModel().put("chatWithUserID", chatWithUserID);
		modelAndView.getModel().put("chatWithUserName", chatWithUserName);

		return modelAndView;
	}

	@MessageMapping("/message/send/{toUserId}")
	public void send(Principal principal, SimpleMessage message, @DestinationVariable String toUserId) {
		System.out.println(message);

		// Get details for sending user (current user)
		String fromUsername = principal.getName();
		Users fromUser = userService.get(fromUsername);
		String fromUserId = fromUser.getUsername();

		// Get details for user we are chatting with.
		Users toUserOpt = userService.get(toUserId);
		Users toUser = toUserOpt;
		String toUsername = toUser.getUsername();

		String returnReceiptQueue = String.format("/queue/%s", toUserId);
		String toUserQueue = String.format("/queue/%s", fromUserId);

		//messageService.save(fromUser, toUser, message.getText());

		message.setIsReply(false);
		simpleMessagingTemplate.convertAndSendToUser(fromUsername, returnReceiptQueue, message);

		message.setFrom(fromUser.getUsername());

		message.setIsReply(true);
		simpleMessagingTemplate.convertAndSendToUser(toUsername, toUserQueue, message);

		// Send a new message notification
		simpleMessagingTemplate.convertAndSendToUser(toUsername, "/queue/newmessages", message);
	}
	
	/* for backup messages
	 @RequestMapping("/chatview/{chatWithUserID}")
	ModelAndView chatView(ModelAndView modelAndView, @PathVariable Long chatWithUserID) {

		Users thisUser = util.getUser();
		String chatWithUserName = userService.getUserName(chatWithUserID);

		modelAndView.setViewName("chatview");
		modelAndView.getModel().put("thisUserID", thisUser.getId());
		modelAndView.getModel().put("chatWithUserID", chatWithUserID);
		modelAndView.getModel().put("chatWithUserName", chatWithUserName);

		return modelAndView;
	}

	@MessageMapping("/message/send/{toUserId}")
	public void send(Principal principal, SimpleMessage message, @DestinationVariable Long toUserId) {
		System.out.println(message);

		// Get details for sending user (current user)
		String fromUsername = principal.getName();
		Users fromUser = userService.get(fromUsername);
		Long fromUserId = fromUser.getId();

		// Get details for user we are chatting with.
		Optional<Users> toUserOpt = userService.get(toUserId);
		Users toUser = toUserOpt.get();
		String toUsername = toUser.getUsername();

		String returnReceiptQueue = String.format("/queue/%d", toUserId);
		String toUserQueue = String.format("/queue/%d", fromUserId);

		//messageService.save(fromUser, toUser, message.getText());

		message.setIsReply(false);
		simpleMessagingTemplate.convertAndSendToUser(fromUsername, returnReceiptQueue, message);

		message.setFrom(fromUser.getUsername());

		message.setIsReply(true);
		simpleMessagingTemplate.convertAndSendToUser(toUsername, toUserQueue, message);

		// Send a new message notification
		simpleMessagingTemplate.convertAndSendToUser(toUsername, "/queue/newmessages", message);
	}
	*/
	 
}
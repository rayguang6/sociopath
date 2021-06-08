package com.sociopath.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.sociopath.model.entity.Users;
import com.sociopath.service.UserService;

@Component
public class Util {
	
	
	@Autowired
	private UserService userService;
	
	public Users getUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		String email = auth.getName();
		
		return userService.get(email);
		
	}
	
	

}

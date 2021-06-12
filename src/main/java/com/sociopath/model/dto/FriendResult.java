package com.sociopath.model.dto;

import java.util.List;
import java.util.Set;

import com.sociopath.model.entity.Student;

public class FriendResult {
	private Long userId;
	private String username;
	private List<Student> listOfFriends;

	public FriendResult(Student student) {
		userId = student.getUser().getId();
		username = student.getUser().getUsername();
		listOfFriends = student.getFriendshipList();
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	

	public List<Student> getListOfFriends() {
		return listOfFriends;
	}

	public void setListOfFriends(List<Student> listOfFriends) {
		this.listOfFriends = listOfFriends;
	}

	@Override
	public String toString() {
		return "FriendResult [userId=" + userId + ", username=" + username + ", listOfFriends=" + listOfFriends + "]";
	}

	
}

package com.sociopath.model.dto;


import java.util.List;
import java.util.Set;

import com.sociopath.model.entity.Student;

public class SearchResult {
	private Long userId;
	private String firstname;
	private List<Student> repFriend;

	public SearchResult(Student student) {
		userId = student.getUser().getId();
		firstname = student.getUser().getUsername();
		repFriend = student.getReputationList();
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}



	public List<Student> getRepFriend() {
		return repFriend;
	}

	public void setRepFriend(List<Student> repFriend) {
		this.repFriend = repFriend;
	}

	@Override
	public String toString() {
		return "SearchResult [userId=" + userId + ", firstname=" + firstname +  ", repFriend="
				+ repFriend + "]";
	}

	

}

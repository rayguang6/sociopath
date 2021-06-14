package com.sociopath.model.dto;


import java.util.List;
import java.util.Set;

import com.sociopath.model.entity.Student;

public class SearchResult {
	
	private Long id;
	private String username;
	
	private String about;
	
	
	
	public SearchResult() {
		super();
	}



	public SearchResult(Long id, String username,  String about) {
		super();
		this.id = id;
		this.username = username;

		this.about = about;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}





	public String getAbout() {
		return about;
	}



	public void setAbout(String about) {
		this.about = about;
	}



	@Override
	public String toString() {
		return "SearchResult [id=" + id + ", username=" + username + ", about=" + about + "]";
	}
	
	
	
	
}
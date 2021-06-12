package com.sociopath.model.dto;

import java.util.ArrayList;

public class CrushGraphStudent {

	private int id;
	
	private String username;
	
	private ArrayList<Integer> crushFriendIdList;
	
	private ArrayList<Integer>[] eventFriendArray;

	
	
	public CrushGraphStudent() {
	}

	


	public CrushGraphStudent(int id, String username) {
		this.id = id;
		this.username = username;
	}

	public CrushGraphStudent(int id, String username, ArrayList<Integer> friendIdList) {
		this.id = id;
		this.username = username;
		this.crushFriendIdList = friendIdList;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public ArrayList<Integer> getCrushFriendIdList() {
		return crushFriendIdList;
	}




	public void setCrushFriendIdList(ArrayList<Integer> crushFriendIdList) {
		this.crushFriendIdList = crushFriendIdList;
	}




	public ArrayList<Integer>[] getEventFriendArray() {
		return eventFriendArray;
	}



	public void setEventFriendArray(ArrayList<Integer>[] eventFriendArray) {
		this.eventFriendArray = eventFriendArray;
	}
	
	
	
	
}

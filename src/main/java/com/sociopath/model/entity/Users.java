package com.sociopath.model.entity;

import javax.validation.constraints.Size;

import org.neo4j.driver.internal.shaded.reactor.util.annotation.NonNull;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.stereotype.Indexed;

@Node(labels = {"Users"})
public class Users {

	@Id
	@GeneratedValue
	private Long id;

	@NonNull
	//迟点要make unique
	private String username;

	private String role;

	private String plainPassword;

	private String password;
		
	private String repeatPassword;
	
	public Users() {
		
	}
	
	public Users(String username) {
		this.username = username;
		this.password = password;
	}
	
	public Users(String username, String password) {
		this.username = username;
		this.plainPassword = password;
		this.repeatPassword = password;
		//this.enabled = true;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPlainPassword() {
		return plainPassword;
	}

	public void setPlainPassword(String plainPassword) {
		this.plainPassword = plainPassword;
	}

	public String getRepeatPassword() {
		return repeatPassword;
	}

	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}

	
}
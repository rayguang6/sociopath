package com.guang.model.entity;

import javax.validation.constraints.Size;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node(labels = {"users"})
public class SiteUser {

	@Id
	@GeneratedValue
	private Long id;

	private String email;

	private String role;

	private String plainPassword;

	private String password;
	
	private String firstname;
	
	private String surname;
	
	private String repeatPassword;
	
	public SiteUser() {
		
	}
	
	public SiteUser(String email, String password) {
		this.email = email;
		this.plainPassword = password;
		this.repeatPassword = password;
		//this.enabled = true;
	}
	
	
	
	public SiteUser(String email, String password, String firstname, String surname) {
		this.email = email;
		this.plainPassword = password;
		this.repeatPassword = password;
		this.firstname = firstname;
		this.surname = surname;
		//this.enabled = true;	
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getRepeatPassword() {
		return repeatPassword;
	}

	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}

	
}
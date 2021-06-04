package com.guang.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class SiteUser {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Column(name = "email", unique=true)
	private String email;

	@Column(name="role", length=20)
	private String role;

	@Transient
	@Size(min=5, max=15, message="{register.password.size}")
	private String plainPassword;

	@Column(name = "password", length=60)
	private String password;
	
	
	
	@Transient
	private String repeatPassword;
	
	public SiteUser() {
		
	}
	
	public SiteUser(String email, String password) {
		this.email = email;
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

	
}
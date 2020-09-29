package com.lti.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table (name = "user_details")
@NamedQuery (name = "login", query = "FROM User WHERE username=:uname AND password=: psw")
public class User {
	@Id
	@Column (length = 20)
	private String username;
	
	@Column (length = 20)
	private String name;
	
	@Column (length = 20)
	private String password;
	
	@Column (length = 20)
	private String dob;
	
	@Column (length = 30)
	private String email;
	
	@Column (length = 40)
	private String address;
	
	@Column (length = 20)
	private String mobile;
	
	@OneToMany(cascade = {CascadeType.ALL}, fetch=FetchType.EAGER)
	private List<Policy> policies = new ArrayList<Policy>();

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Policy> getPolicies() {
		return policies;
	}

	public void setPolicies(List<Policy> policies) {
		this.policies = policies;
	}
}

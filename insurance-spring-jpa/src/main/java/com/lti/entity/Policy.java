package com.lti.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table (name = "policies")
public class Policy {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "pol_id")
	private int pid;
	
	@Column (length = 20)
	private String type;

	private int Term;
	
	@Column (length = 20)
	private String expDate;
	
	@OneToMany(mappedBy = "pol", cascade = {CascadeType.ALL}, fetch=FetchType.EAGER)
	private List<User> users = new ArrayList<User>();

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getTerm() {
		return Term;
	}

	public void setTerm(int term) {
		Term = term;
	}

	public String getExpDate() {
		return expDate;
	}

	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
}

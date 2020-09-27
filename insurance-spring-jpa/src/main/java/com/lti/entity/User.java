package com.lti.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table (name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "user_id")
	private int uid;
	
	@Column (length = 20)
	private String username;
	
	@Column (length = 20)
	private String password;
	
	@Column (length = 20)
	private String dob;
	
	@Column (length = 20)
	private String address;
	
	@Column (length = 20)
	private String mobile;
	
	@ManyToOne
	@JoinColumn(name = "pol_id")
	private Policy pol;
	
	@OneToOne (cascade = {CascadeType.ALL}, fetch=FetchType.EAGER)
	private Claim claim;
	
	
}

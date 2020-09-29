package com.lti.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table (name = "policy_details")
public class Policy {
	@Id
	@Column(name = "pol_no")
	private String pno;
	
	@Column (length = 20)
	private String type;

	private int Term;
	
	@Column (length = 20)
	private String expDate;
	
	@OneToOne
	@JoinColumn(name = "claim_id")
	private Claim claim;
	
	@OneToOne
	@JoinColumn (name = "reg_no")
	private Vehicle vehicle;
	
	public String getPno() {
		return pno;
	}

	public void setPno(String pno) {
		this.pno = pno;
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

	public Claim getClaim() {
		return claim;
	}

	public void setClaim(Claim claim) {
		this.claim = claim;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
}

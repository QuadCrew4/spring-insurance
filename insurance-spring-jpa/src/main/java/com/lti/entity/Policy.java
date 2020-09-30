/**
 * @author 
 * @version
 */

package com.lti.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/* This class represents an entity for Policy */

@Entity
@Table (name = "policy_details")
public class Policy {
	@Id
	@Column(name = "pol_no")
	private String policyNo;
	
	@Column (length = 20)
	private String type;

	private int term;
	
	@Column (length = 20)
	private String expDate;
	
	@OneToOne(cascade = {CascadeType.ALL}, fetch=FetchType.EAGER)
	@JoinColumn(name = "claim_id")
	private Claim claim;
	
	@OneToOne
	@JoinColumn (name = "reg_no")
	private Vehicle vehicle;
	
	public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String pno) {
		this.policyNo = pno;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getTerm() {
		return term;
	}

	public void setTerm(int term) {
		this.term = term;
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

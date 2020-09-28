package com.lti.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
}

/**
 * @author Parnab
 * @version 1.0
 */

package com.lti.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/* This class represents an entity for Claim */

@Entity
@Table (name = "claim_details")
public class Claim {
	@Id
	@Column(name = "claim_id")
	private String claimId;
	
	private double amount;
	
	@Column(length = 20)
	private String status;
	
	@Column(length = 20)
	private String claimDate;
	
	@Column (length = 50)
	private String reason;

	public String getClaimId() {
		return claimId;
	}

	public void setClaimId(String cid) {
		this.claimId = cid;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getClaimDate() {
		return claimDate;
	}

	public void setClaimDate(String claimDate) {
		this.claimDate = claimDate;
	}
}

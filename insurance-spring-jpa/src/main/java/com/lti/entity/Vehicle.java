package com.lti.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="vehicle_details")
public class Vehicle {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int vid;
	
	@Column(length = 20)
	private String regNo;
	
	@Column(length = 20)
	private String mfr;
	
	@Column(length = 20)
	private String model;
	
	private double price;
	
	@Column(length = 20)
	private String dlicence;
	
	@Column(length = 20)
	private String pdate;
	
	@Column(length = 20)
	private String engNo;
	
	@Column(length = 20)
	private String chNo;

	public int getVid() {
		return vid;
	}

	public void setVid(int vid) {
		this.vid = vid;
	}

	public String getRegNo() {
		return regNo;
	}

	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}

	public String getMfr() {
		return mfr;
	}

	public void setMfr(String mfr) {
		this.mfr = mfr;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDlicence() {
		return dlicence;
	}

	public void setDlicence(String dlicence) {
		this.dlicence = dlicence;
	}

	public String getPdate() {
		return pdate;
	}

	public void setPdate(String pdate) {
		this.pdate = pdate;
	}

	public String getEngNo() {
		return engNo;
	}

	public void setEngNo(String engNo) {
		this.engNo = engNo;
	}

	public String getChNo() {
		return chNo;
	}

	public void setChNo(String chNo) {
		this.chNo = chNo;
	}
}

/**
 * @author Parnab
 * @version 1.0
 */
package com.lti.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/* This class represents an entity for Vehicle */

@Entity
@Table (name="vehicle_details")
public class Vehicle {
	@Id
	@Column(length = 20, name = "reg_no")
	private String regNo;
	
	@Column(length = 10)
	private String type;
	
	@Column(length = 20)
	private String manufacturer;
	
	@Column(length = 20)
	private String model;
	
	private double price;
	
	@Column(length = 20)
	private String driverLicence;
	
	@Column(length = 20)
	private String purchaseDate;
	
	@Column(length = 20)
	private String engineNo;
	
	@Column(length = 20)
	private String chasisNo;

	public String getRegNo() {
		return regNo;
	}

	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String mfr) {
		this.manufacturer = mfr;
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

	public String getDriverLicence() {
		return driverLicence;
	}

	public void setDriverLicence(String dlicence) {
		this.driverLicence = dlicence;
	}

	public String getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(String pdate) {
		this.purchaseDate = pdate;
	}

	public String getEngineNo() {
		return engineNo;
	}

	public void setEngineNo(String engNo) {
		this.engineNo = engNo;
	}

	public String getChasisNo() {
		return chasisNo;
	}

	public void setChasisNo(String chNo) {
		this.chasisNo = chNo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}

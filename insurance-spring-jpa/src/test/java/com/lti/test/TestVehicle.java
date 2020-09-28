package com.lti.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lti.entity.Vehicle;
import com.lti.repo.VehicleRepo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration ("classpath:appctx.xml")
public class TestVehicle {

	@Autowired
	private VehicleRepo repo;
	
	@Test
	public void testAddVehicleDetails() {
		Vehicle v = new Vehicle();
		v.setRegNo("ABCD4567");
		v.setMfr("Honda");
		v.setModel("CB Shine");
		v.setType("Bike");
		v.setPdate("11/05/2015");
		v.setPrice(50000);
		v.setDlicence("MHK20WB21");
		v.setEngNo("EGN201556");
		v.setChNo("CHS201575");
		
		repo.saveVehicle(v);
	}
	
	@Test
	public void testFetchVehicle() {
		Vehicle v = repo.fetch("ABCD4567");
		
		System.out.println(v.getMfr()+ "\t" + v.getModel());
	}
}

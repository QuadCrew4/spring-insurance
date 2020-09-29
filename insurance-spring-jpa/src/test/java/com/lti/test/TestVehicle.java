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
	
	/* Test cases for adding vehicle details and fetching a record */
	
	@Test
	public void testAddVehicleDetails() {
		Vehicle v = new Vehicle();
		v.setRegNo("CKJH4867");
		v.setMfr("Ferrari");
		v.setModel("Tributo");
		v.setType("Car");
		v.setPdate("05/05/2016");
		v.setPrice(60000);
		v.setDlicence("MHK21WC21");
		v.setEngNo("EGN301576");
		v.setChNo("CHS201775");
		
		repo.saveVehicle(v);
	}
	
	@Test
	public void testFetchVehicle() {
		Vehicle v = repo.fetch("ABCD4567");
		
		System.out.println(v.getMfr()+ "\t" + v.getModel());
	}
}

package com.lti.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lti.entity.Claim;
import com.lti.entity.Policy;
import com.lti.entity.User;
import com.lti.entity.Vehicle;
import com.lti.pojo.Login;
import com.lti.repo.UserRepo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:appctx.xml")
public class TestUserPolicyClaim {

	@Autowired
	private UserRepo repo;

	/* Test for adding user with claim & policy */

	/*
	 * @Test public void testSaveUserPolicyClaim() { User u = new User();
	 * u.setUsername("parnab"); u.setName("Parnab Das"); u.setPassword("random");
	 * u.setDob("09/12/1997"); u.setEmail("parnab.das@gmail.com");
	 * u.setAddress("Saltlake"); u.setMobile("9748096259");
	 * 
	 * Policy p = repo.fetchUserPolicy("ANXH1234"); Claim c =
	 * repo.fetchUserClaim("CL123");
	 * 
	 * u.setPolicy(p); u.setClaim(c);
	 * 
	 * repo.saveUser(u); }
	 */

	/* Test for adding user */

	@Test
	public void testAddUser() {
		User u = new User();
		u.setUsername("shilpi");
		u.setName("Shilpi kundu");
		u.setPassword("justatest");
		u.setDob("10/04/1995");
		u.setEmail("xyz@gmail.com");
		u.setAddress("Park Street");
		u.setMobile("5456467859");

		repo.saveUser(u);
	}

	/* Test for adding policy */

	@Test
	public void testAddPolicy() {
		Policy p = new Policy();
		p.setPno("BHGD1854");
		p.setType("Third Party");
		p.setTerm(3);
		p.setExpDate("05/09/2022");

		repo.savePolicy(p);
	}

	/* Test for adding claim */

	@Test
	public void testAddClaim() {
		Claim c = new Claim();
		c.setCid("CL123");
		c.setAmount(30000);
		c.setReason("Accident");

		repo.saveClaim(c);
	}

	/* Test for adding vehicle */

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

	/* Test for login validation */

	@Test
	public void testLogin() {
		Login login = new Login("shilpi", "justatest");
		User u = repo.authenticate(login);
		System.out.println(u.getUsername() + "\t" + u.getPassword());
	}

	/* Test for fetching user vehicle */

	@Test
	public void testFetchVehicle() {
		Vehicle v = repo.fetchVehicle("CKJH4867");

		System.out.println(v.getMfr() + "\t" + v.getModel());
	}

	/* Test for fetching user policy */

	@Test
	public void testFetchUserPolicies() {
		User u = repo.fetchUser("shilpi");
		List<Policy> policies = u.getPolicies();
		System.out.println("User: " + u.getName());
		for (Policy p : policies) {
			System.out.println("Policy No\tPolicy Type\tExpiry Date");
			System.out.println(p.getPno() + "\t" + p.getType() + "\t" + p.getExpDate());
		}
	}

	/* Test for claim history */

	@Test
	public void testfetchUserClaims() {
		User u = repo.fetchUser("shilpi");
		List<Policy> policies = u.getPolicies();
		System.out.println("User: " + u.getName());
		for (Policy p : policies) {
			System.out.println(
					"Policy No\tPolicy Type\tExpiry Date\t\tClaim No\tClaim Amount\tClaim Reason\tClaim Status");
			System.out.println(p.getPno() + "\t" + p.getType() + "\t" + p.getExpDate() + "\t\t" + p.getClaim().getCid()
					+ "\t\t" + p.getClaim().getAmount() + "\t\t" + p.getClaim().getReason() + "\t\t"
					+ p.getClaim().getStatus());
		}
	}

	/* Test for fetching all claim histories */

	@Test
	public void testFetchAllClaimHistories() {
		List<User> users = repo.fetchAll();
		for (User u : users) {
			List<Policy> policies = u.getPolicies();
			System.out.println("User: " + u.getName());
			for (Policy p : policies) {
				System.out.println("Claim No\tClaim Amount\tClaim Reason\tClaim Status");
				System.out.println(p.getClaim().getCid() + "\t\t" + p.getClaim().getAmount() + "\t\t"
						+ p.getClaim().getReason() + "\t\t" + p.getClaim().getStatus());
			}
		}
	}

	/* Test for buying an insurance */

	@Test
	public void testAddUserPolicy() {
		User u = repo.fetchUser("shilpi");
		Policy p = repo.fetchPolicy("BHGD1854");
		u.getPolicies().add(p);
		repo.addUserPolicies(u);
	}

	/* Test for claiming an insurance */

	@Test
	public void testAddUserPolicyClaim() {
		User u = repo.fetchUser("shilpi");
		Claim c = repo.fetchClaim("CL123");
		List<Policy> policies = u.getPolicies();
		for (Policy p : policies) {
			if(p.getPno().equals("BHGD1854"))
				repo.addPolicyClaim(p, c);
		}
		repo.updateUser(u);
	}
	
	/*add a test for adding vehicle to policy and user and fetch it from db same as claim*/
	
	/*test to fetch claim details for a particular user and his particular policy*/
	
	@Test
	public void testFetchUserClaim() {
		User u = repo.fetchUser("shilpi");
		Claim c= new Claim();
		List<Policy> policies = u.getPolicies();
		for (Policy p : policies) {
			if(p.getPno().equals("BHGD1854"))
				c = p.getClaim();
		}
		System.out.println("User: " + u.getName()+"\tPolicy No: BHGD1854");
		System.out.println("Claim No\tClaim Amount\tClaim Reason\tClaim Status");
		System.out.println(c.getCid() + "\t\t" + c.getAmount() + "\t\t"
				+ c.getReason() + "\t\t" + c.getStatus());
	}
	/*
	 * Test for renewing an insurance
	 * 
	 * @Test public void testRenewPolicy() { User u = repo.fetchUser("parnab");
	 * Policy p = repo.fetchUserPolicy(u.getPolicy().getPno());
	 * repo.renewInsurance(p,"20/11/2050"); }
	 * 
	 * Test cases for admin
	 * 
	 * @Test public void testSetUserClaim() { User u = repo.fetchUser("parnab");
	 * Claim c = repo.fetchUserClaim(u.getClaim().getCid());
	 * repo.setUserClaim(c,"Approved",25000.00); }
	 * 
	 * @Test public void testRemoveUserClaim() { repo.removeUserClaim("parnab"); }
	 */
}

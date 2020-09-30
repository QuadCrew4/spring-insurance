/**
 * @author 
 * @version
 */

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

/* This class contains test cases for all entities*/

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:appctx.xml")
public class TestUserPolicyClaim {

	@Autowired
	private UserRepo repo;

	/* Test for adding user */

	@Test
	public void testAddUser() {
		User u = new User();
		u.setUsername("parnab");
		u.setName("Parnab Das");
		u.setPassword("random");
		u.setDob("09/12/1997");
		u.setEmail("parnab@gmail.com");
		u.setAddress("Saltlake");
		u.setMobile("5456467859");

		repo.saveUser(u);
	}

	/* Test for adding policy */

	@Test
	public void testAddPolicy() {
		Policy p = new Policy();
		p.setPolicyNo("XYXZ1234");
		p.setType("Comprehensive");
		p.setTerm(2);
		p.setExpDate("11/07/2023");

		p.setClaim(null);
		p.setVehicle(null);
		
		repo.savePolicy(p);
	}

	/* Test for adding claim */

	@Test
	public void testAddClaim() {
		Claim c = new Claim();
		c.setClaimId("CL582");
		c.setAmount(50000);
		c.setClaimDate("20/09/2020");
		c.setReason("Calamity");

		repo.saveClaim(c);
	}

	/* Test for adding vehicle */

	@Test
	public void testAddVehicleDetails() {
		Vehicle v = new Vehicle();
		v.setRegNo("WHFG8542");
		v.setManufacturer("Maruti Suziki");
		v.setModel("Swift");
		v.setType("Car");
		v.setPurchaseDate("05/05/2016");
		v.setPrice(70000);
		v.setDriverLicence("MHK21WC21");
		v.setEngineNo("EGN301579");
		v.setChasisNo("CHS201775");

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

		System.out.println(v.getManufacturer() + "\t" + v.getModel());
	}

	/* Test for fetching user policy */

	@Test
	public void testFetchUserPolicies() {
		User u = repo.fetchUser("parnab");
		List<Policy> policies = u.getPolicies();
		System.out.println("User: " + u.getName());
		for (Policy p : policies) {
			System.out.println("Policy No\tPolicy Type\tExpiry Date");
			System.out.println(p.getPolicyNo() + "\t" + p.getType() + "\t" + p.getExpDate());
		}
	}

	/* Test for claim history */

	@Test
	public void testfetchUserClaims() {
		User u = repo.fetchUser("parnab");
		List<Policy> policies = u.getPolicies();
		System.out.println("User: " + u.getName());
		for (Policy p : policies) {
			if(p.getClaim() == null){
				System.out.println(
						"Policy No\tPolicy Type\tExpiry Date\t\tClaim No\tClaim Amount\tClaim Date\tClaim Reason\tClaim Status");
				System.out.println(p.getPolicyNo() + "\t" + p.getType() + "\t" + p.getExpDate() + "\t\t" + "NA"
						+ "\t\t" + "NA" + "\t\t" + "NA" + "\t\t" + "NA" + "\t\t"
						+ "NA");
			}
			else {
				System.out.println(
						"Policy No\tPolicy Type\tExpiry Date\t\tClaim No\tClaim Amount\tClaim Date\tClaim Reason\tClaim Status");
				System.out.println(p.getPolicyNo() + "\t" + p.getType() + "\t" + p.getExpDate() + "\t\t" + p.getClaim().getClaimId()
						+ "\t\t" + p.getClaim().getAmount() + "\t\t" + p.getClaim().getClaimDate() + "\t\t" + p.getClaim().getReason() + "\t\t"
						+ p.getClaim().getStatus());
			}
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
				System.out.println("Policy Number: "+p.getPolicyNo());
				if(p.getClaim() == null){
					System.out.println("Claim No\tClaim Amount\tClaim Date\tClaim Reason\tClaim Status");
					System.out.println("NA" + "\t\t" + "NA" + "\t\t"+ "NA" + "\t\t"
							+ "NA" + "\t\t" + "NA");
				}
				else {
					System.out.println("Claim No\tClaim Amount\tClaim Date\tClaim Reason\tClaim Status");
					System.out.println(p.getClaim().getClaimId() + "\t\t" + p.getClaim().getAmount() + "\t\t"+ p.getClaim().getClaimDate() + "\t\t"
							+ p.getClaim().getReason() + "\t\t" + p.getClaim().getStatus());
				}
				
			}
		}
	}

	/* Test for buying an insurance */

	@Test
	public void testAddUserPolicy() {
		User u = repo.fetchUser("parnab");
		Policy p = repo.fetchPolicy("XYXZ1234");
		u.getPolicies().add(p);
		repo.addUserPolicies(u);
	}

	/* Test for claiming an insurance */

	@Test
	public void testAddUserPolicyClaim() {
		User u = repo.fetchUser("parnab");
		Claim c = repo.fetchClaim("CL789");
		List<Policy> policies = u.getPolicies();
		for (Policy p : policies) {
			if(p.getPolicyNo().equals("MNOP4576"))
				repo.addPolicyClaim(p, c);
		}
		repo.updateUser(u);
	}
	
	/*add a test for adding vehicle to policy and user and fetch it */
	
	@Test
	public void testAddUserPolicyVehicle() {
		User u = repo.fetchUser("parnab");
		Vehicle v = repo.fetchVehicle("HJSK8958");	
		List<Policy> policies = u.getPolicies();
		for (Policy p : policies) {
			if(p.getPolicyNo().equals("MNOP4576"))
				repo.addVehiclePolicy(p, v);		
		}
		repo.updateUser(u);
	}
	
	/*test to fetch vehicle details for a particular user and his particular policy*/
	
	@Test
	public void testfetchUserVehicle() {
		User u = repo.fetchUser("parnab");
		List<Policy> policies = u.getPolicies();
		System.out.println("User: " + u.getName());
		for (Policy p : policies) {
			System.out.println(
					"Policy No\tPolicy Type\tExpiry Date\t\tVehicle Registration number\t\tModel\tVehicle type");
			System.out.println(p.getPolicyNo() + "\t" + p.getType() + "\t" + p.getExpDate() + "\t\t\t" + 
					p.getVehicle().getRegNo()+ "\t\t\t" + p.getVehicle().getModel()+"\t"+p.getVehicle().getType());
		}
	}
	
	
	/*test to fetch claim details for a particular user and his particular policy*/
	
	@Test
	public void testFetchUserClaim() {
		User u = repo.fetchUser("shilpi");
		Claim c= new Claim();
		List<Policy> policies = u.getPolicies();
		for (Policy p : policies) {
			if(p.getPolicyNo().equals("BHGD1854"))
				c = p.getClaim();
		}
		if(c != null) {
			System.out.println("User: " + u.getName()+"\tPolicy No: BHGD1854");
			System.out.println("Claim No\tClaim Amount\tClaim Date\tClaim Reason\tClaim Status");
			System.out.println(c.getClaimId() + "\t\t" + c.getAmount() + "\t\t"+ c.getClaimDate() + "\t"
					+ c.getReason() + "\t" + c.getStatus());
		}
	}
	
/*test to fetch vehicle details for a particular user and his particular policy*/
	
	@Test
	public void testFetchUserVehicle() {
		User u = repo.fetchUser("shilpi");
		Vehicle v = new Vehicle();
		List<Policy> policies = u.getPolicies();
		for (Policy p : policies) {
			if(p.getPolicyNo().equals("BHGD1854"))
				v = p.getVehicle();
		}
		System.out.println("User: " + u.getName()+"\tPolicy No: BHGD1854");
		System.out.println("Vehicle Registration number\t\tModel");
		System.out.println(v.getRegNo()+"\t\t"+v.getModel());
	}
	
	
	/* Test for renewing an insurance*/
	
	@Test 
	public void testRenewPolicy() { 
		User u = repo.fetchUser("shilpi");
		List<Policy> policies = u.getPolicies();
		for (Policy p : policies) {
			if(p.getPolicyNo().equals("BHGD1854"))
				p.setExpDate("05/09/2025");
		}
		repo.updateUser(u);		
	}
	
	/* test for admin to set the claim amount and status*/		
	
	@Test 
	public void testSetUserClaim() { 
		
		User u = repo.fetchUser("shilpi");
		Claim c = new Claim();
		List<Policy> policies = u.getPolicies();
		for (Policy p : policies) {
			if(p.getPolicyNo().equals("BHGD1854")) {
				c=p.getClaim();
				c.setAmount(27000);
				c.setStatus("Rejected");				
			}
		}
		repo.updateUser(u);
	}	
	
	/*Test for removing an user claim*/
	
	@Test
	public void testRemoveUserClaim() {
		User u = repo.fetchUser("shilpi");
		Claim c = null;
		List<Policy> policies = u.getPolicies();
		for (Policy p : policies) {
			if(p.getPolicyNo().equals("BHGD1854")) {
				/* c=p.getClaim(); */
				p.setClaim(c);
			}
		}
		repo.updateUser(u);
	}
	
	/*Test for removing a claim*/
	
	@Test
	public void testRemoveClaim() {
		repo.removeClaim("CL123");
	}
	
	/*Test for removing an user policy*/
	
	@Test
	public void testRemoveUserPolicies() {
		User u = repo.fetchUser("parnab");
		u.getPolicies().removeAll(u.getPolicies());
		repo.updateUser(u);
	}
	
	/*Test for removing a policy*/
	
	@Test
	public void testRemovePolicy() {
		repo.removePolicy("MNOP4576");
	}
}

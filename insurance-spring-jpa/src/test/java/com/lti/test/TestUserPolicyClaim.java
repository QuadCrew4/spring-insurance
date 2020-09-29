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
import com.lti.pojo.Login;
import com.lti.repo.UserRepo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration ("classpath:appctx.xml")
public class TestUserPolicyClaim {
	
	@Autowired
	private UserRepo repo;
	
	/* Test for adding user with claim & policy */
	
	@Test
	public void testSaveUserPolicyClaim() {
		User u = new User();
		u.setUsername("parnab");
		u.setName("Parnab Das");
		u.setPassword("random");
		u.setDob("09/12/1997");
		u.setEmail("parnab.das@gmail.com");
		u.setAddress("Saltlake");
		u.setMobile("9748096259");
		
		Policy p = repo.fetchUserPolicy("ANXH1234");
		Claim c = repo.fetchUserClaim("CL123");
		
		u.setPolicy(p);
		u.setClaim(c);
		
		repo.saveUser(u);
	}
	
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
		Policy p =new Policy();
		p.setPno("BHGD1854");
		p.setType("Third Party");
		p.setTerm(3);
		p.setExpDate("05/09/2022");
		
		repo.savePolicy(p);
	}
	
	/* Test for adding claim */
	
	@Test
	public void testAddClaim() {
		Claim c=new Claim();
		c.setCid("CL123");
		c.setAmount(30000);
		c.setReason("Accident");
		
		repo.saveClaim(c);
	}
	
	/* Test for login validation */

	@Test
	public void testLogin() {
		Login login = new Login("parnab","random");
		User u = repo.authenticate(login);
		System.out.println(u.getUsername()+"\t"+u.getPassword());
	}
	
	/* Test for fetching user policy */
	
	@Test
	public void testFetchUserPolicy() {
		User u = repo.fetchUser("parnab");
		System.out.println("User\t\tPolicy No\tPolicy Type\tExpiry Date");
		System.out.println(u.getName()+"\t"+u.getPolicy().getPno()+"\t"+u.getPolicy().getType()+"\t"+u.getPolicy().getExpDate());
	}
	
	/* Test for claim history */
	
	@Test
	public void testfetchUserClaim() {
		User u = repo.fetchUser("parnab");
		System.out.println("User\t\tClaim No\tClaim Amount\tClaim Reason\tClaim Status");
		System.out.println(u.getName()+"\t"+u.getClaim().getCid()+"\t\t"+u.getClaim().getAmount()+"\t\t"+u.getClaim().getReason()+"\t\t"+u.getClaim().getStatus());
	}
	
	/* Test for fetching all claim histories */
	
	@Test
	public void testFetchAllClaimHistories() {
		List<User> users = repo.fetchAll();
		for (User u : users) {
			System.out.println("User\t\tClaim No\tClaim Amount\tClaim Reason\tClaim Status");
			System.out.println(u.getName()+"\t"+u.getClaim().getCid()+"\t\t"+u.getClaim().getAmount()+"\t\t"+u.getClaim().getReason()+"\t\t"+u.getClaim().getStatus());
		}
	}
	
	/* Test for buying an insurance */
	
	
	@Test
	public void testAddUserPolicy() {
		User u = repo.fetchUser("parnab");
		Policy p = repo.fetchUserPolicy("BHGD1854");
		repo.addUserPolicy(u, p);
	}
	
	/* Test for claiming an insurance */
	
	@Test
	public void testAddUserClaim() {
		User u = repo.fetchUser("shilpi");
		Claim c = repo.fetchUserClaim("CL123");
		repo.addUserClaim(u, c);
	}
	
	/* Test for renewing an insurance */
	
	@Test
	public void testRenewPolicy() {
		User u = repo.fetchUser("parnab");
		Policy p = repo.fetchUserPolicy(u.getPolicy().getPno());
		repo.renewInsurance(p,"20/11/2050");
	}
	
	/* Test cases for admin */
	
	@Test
	public void testSetUserClaim() {
		User u = repo.fetchUser("parnab");
		Claim c = repo.fetchUserClaim(u.getClaim().getCid());
		repo.setUserClaim(c,"Approved",25000.00);
	}
	
	@Test
	public void testRemoveUserClaim() {
		repo.removeUserClaim("parnab");
	}
}

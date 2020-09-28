package com.lti.test;


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
	
	@Test
	public void testAddPolicy() {
		Policy p =new Policy();
		p.setPno("ANXH1234");
		p.setType("Comprehensive");
		p.setTerm(2);
		p.setExpDate("11/12/2021");
		
		repo.savePolicy(p);
	}
	
	@Test
	public void testAddClaim() {
		Claim c=new Claim();
		c.setCid("CL123");
		c.setAmount(24000);
		c.setReason("Accident");
		
		repo.saveClaim(c);
	}

	@Test
	public void testLogin() {
		Login login = new Login("parnab","random");
		User u = repo.authenticate(login);
		System.out.println(u.getUsername()+"\t"+u.getPassword());
	}
	
	@Test
	public void testFetchUserPolicy() {
		User u = repo.fetchUser("parnab");
		System.out.println("User\t\tPolicy No\tPolicy Type\tExpiry Date");
		System.out.println(u.getName()+"\t"+u.getPolicy().getPno()+"\t"+u.getPolicy().getType()+"\t"+u.getPolicy().getExpDate());
	}
	
	@Test
	public void testfetchUserClaim() {
		User u = repo.fetchUser("parnab");
		System.out.println("User\t\tClaim No\tClaim Amount\tClaim Reason");
		System.out.println(u.getName()+"\t"+u.getClaim().getCid()+"\t\t"+u.getClaim().getAmount()+"\t\t"+u.getClaim().getReason());
	}
}

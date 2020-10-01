package com.lti.repo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.lti.entity.Claim;
import com.lti.entity.Policy;
import com.lti.entity.User;

@Repository
public class ClaimRepoImpl implements ClaimRepo {
	
	@PersistenceContext
	private EntityManager em;

	public void saveClaim(Claim c) {
		em.persist(c);
	}

	public Claim fetchClaim(String cno) {
		Claim c = em.find(Claim.class, cno);
		return c;
	}

	public List<Claim> fetchClaims() {
		return em.createQuery("from Claim").getResultList();
	}

	public void removeClaim(String claimId) {
		Claim c =em.find(Claim.class,claimId);
		em.remove(c);
	}
	
	public void addPolicyClaim(String policyNo,String claimId) {
		Policy p = em.find(Policy.class, policyNo);
		Claim c = em.find(Claim.class, claimId);
		p.setClaim(c);
		em.merge(p);
	}
	public void addUserPolicyClaim(String uname, String policyNo, String claimId) {
		User u = em.find(User.class, uname);
		Claim c = em.find(Claim.class, claimId);
		List<Policy> policies = u.getPolicies();
		for (Policy p : policies) {
			if(p.getPolicyNo().equals(policyNo))
				addPolicyClaim(policyNo, claimId);
		}
		em.merge(u);
	}

	public void setUserClaim(String uname, String policyNo, Double amount, String status) {
		User u = em.find(User.class, uname);
		Claim c = new Claim();
		List<Policy> policies = u.getPolicies();
		for (Policy p : policies) {
			if(p.getPolicyNo().equals(policyNo)) {
				c=p.getClaim();
				c.setAmount(amount);
				c.setStatus(status);				
			}
		}
		em.merge(u);
	}

	public void removeUserClaim(String uname, String policyNo) {
		User u = em.find(User.class, uname);
		Claim c = null;
		List<Policy> policies = u.getPolicies();
		for (Policy p : policies) {
			if(p.getPolicyNo().equals(policyNo)) {
				p.setClaim(c);
			}
		}
		em.merge(u);
	}

}

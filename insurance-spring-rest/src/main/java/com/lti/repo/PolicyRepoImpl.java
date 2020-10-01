package com.lti.repo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.lti.entity.Claim;
import com.lti.entity.Policy;
import com.lti.entity.User;
import com.lti.entity.Vehicle;

@Repository
public class PolicyRepoImpl implements PolicyRepo {
	
	@PersistenceContext
	private EntityManager em;

	public void savePolicy(Policy p) {
		em.persist(p);
	}

	public Policy fetchPolicy(String pno) {
		Policy p = em.find(Policy.class, pno);
		return p;
	}

	public List<Policy> fetchPolicies() {
		
		return em.createQuery("from Policy").getResultList();
	}

	public void addUserPolicies(String uname, String policyNo) {
		User u=em.find(User.class,uname);
		Policy p=em.find(Policy.class, policyNo);
		u.getPolicies().add(p);
		em.merge(u);
	}

	public void removePolicy(String policyNo) {
		Policy p =em.find(Policy.class,policyNo);
		em.remove(p);
	}

	public void renewUserpolicy(String uname, String policyNo, String expDate) {
		User u = em.find(User.class, uname);
		List<Policy> policies = u.getPolicies();
		for (Policy p : policies) {
			if(p.getPolicyNo().equals(policyNo))
				p.setExpDate(expDate);
		}
		em.merge(u);		
	}

	public void removeUserPolicies(String uname) {
		User u =em.find(User.class,uname);
		u.getPolicies().removeAll(u.getPolicies());
		em.merge(u);
	}

}

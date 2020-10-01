package com.lti.repo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.lti.entity.Policy;
import com.lti.entity.User;
import com.lti.entity.Vehicle;

@Repository
public class VehicleRepoImpl implements VehicleRepo {

	@PersistenceContext
	private EntityManager em;
	
	public void saveVehicle(Vehicle v) {
		em.persist(v);
	}

	public Vehicle fetchVehicle(String regno) {
		Vehicle v = em.find(Vehicle.class, regno);
		return v;
	}

	public void addVehiclePolicy(String policyNo,String regNo) {
		Policy p = em.find(Policy.class, policyNo);
		Vehicle v = em.find(Vehicle.class, regNo);
		p.setVehicle(v);
		em.merge(p);
	}
	
	public void addUserPolicyVehicle(String uname, String policyNo, String regNo) {
		User u = em.find(User.class, uname);
		Vehicle v = em.find(Vehicle.class, regNo);
		List<Policy> policies = u.getPolicies();
		for (Policy p : policies) {
			if(p.getPolicyNo().equals(policyNo))
				addVehiclePolicy(policyNo, regNo);
		}
		em.merge(u);
	}
}

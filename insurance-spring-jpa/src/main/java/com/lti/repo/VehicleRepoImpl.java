package com.lti.repo;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.stereotype.Repository;

import com.lti.entity.Vehicle;

@Repository
public class VehicleRepoImpl implements VehicleRepo {

	@PersistenceContext
	private EntityManager em;
	
	@Transactional(value = TxType.REQUIRED)
	public void saveVehicle(Vehicle v) {
		em.persist(v);
	}

	public Vehicle fetch(String reg) {
		Vehicle v = em.find(Vehicle.class, reg);
		return v;
	}
}

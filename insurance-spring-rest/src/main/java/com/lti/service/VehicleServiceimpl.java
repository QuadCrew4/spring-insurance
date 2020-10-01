package com.lti.service;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.entity.Vehicle;
import com.lti.repo.VehicleRepo;

@Service
public class VehicleServiceimpl implements VehicleService {
	
	@Autowired
	private VehicleRepo repo;

	@Transactional(value = TxType.REQUIRED)
	public void persistVehicle(Vehicle v) {
		repo.saveVehicle(v);
	}

	public Vehicle findVehicle(String regNo) {
		return repo.fetchVehicle(regNo);
	}

	@Transactional(value = TxType.REQUIRED)
	public void editVehiclePolicy(String policyNo,String regNo) {
		repo.addVehiclePolicy(policyNo, regNo);
	}
	
	@Transactional(value = TxType.REQUIRED)
	public void editUserPolicyVehicle(String uname, String policyNo, String regNo) {
		repo.addUserPolicyVehicle(uname, policyNo, regNo);
	}

}

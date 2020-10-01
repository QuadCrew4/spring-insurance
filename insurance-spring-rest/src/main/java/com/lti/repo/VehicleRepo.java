package com.lti.repo;

import com.lti.entity.Vehicle;

public interface VehicleRepo {

	void saveVehicle(Vehicle v);
	Vehicle fetchVehicle(String regno);
	void addVehiclePolicy(String policyNo,String regNo);
	void addUserPolicyVehicle(String uname,String policyNo, String regNo);
}

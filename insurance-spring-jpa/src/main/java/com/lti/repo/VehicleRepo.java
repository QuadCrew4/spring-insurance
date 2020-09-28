package com.lti.repo;


import com.lti.entity.Vehicle;


public interface VehicleRepo {
	void saveVehicle(Vehicle v);
	Vehicle fetch(String reg);
}

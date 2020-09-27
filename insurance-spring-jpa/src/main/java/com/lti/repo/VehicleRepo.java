package com.lti.repo;

import java.util.List;

import com.lti.entity.Vehicle;


public interface VehicleRepo {
	void saveVehicle(Vehicle v);
	Vehicle fetch(int vid);
	List<Vehicle> fetchAll();
}

package com.lti.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.entity.Vehicle;
import com.lti.service.VehicleService;

@CrossOrigin
@RestController
public class VehicleRestController {

	@Autowired
	private VehicleService service;
	
	@PostMapping(value="/addvehicle",consumes="application/json")
	public String addVehicle(@RequestBody Vehicle vehicle) {
		service.persistVehicle(vehicle);
		return "Vehicle added successfully";
	}
	
	@PutMapping(value="/addpolicyvehicle/{policyNo},{regNo}",consumes="application/json")
	public String addPolicyVehicle(@PathVariable String policyNo,@PathVariable String regNo)
	{
		service.editVehiclePolicy(policyNo, regNo);
		return "Vehicle added to Policy successfully";
	}
	
	@PutMapping(value="/adduserpolicyvehicle/{uname},{policyNo},{regNo}",consumes="application/json")
	public String addUserPolicyVehicle(@PathVariable String uname,@PathVariable String policyNo,@PathVariable String regNo)
	{
		service.editUserPolicyVehicle(uname, policyNo, regNo);
		return "Vehicle added to an User's Policy successfully";
	}
	
	
}

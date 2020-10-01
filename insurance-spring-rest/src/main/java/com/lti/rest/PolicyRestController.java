package com.lti.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.entity.Policy;
import com.lti.service.PolicyService;

@CrossOrigin
@RestController
public class PolicyRestController {

	@Autowired
	private PolicyService service;
	
	@PostMapping(value="/addpolicy",consumes="application/json")
	public String addPolicy(@RequestBody Policy policy) {
		service.persistPolicy(policy);
		return "Policy added successfully";
	}
	
	@PutMapping(value="/adduserpolicy/{uname},{policyNo}",consumes="application/json")
	public String addUserPolicy(@PathVariable String uname,@PathVariable String policyNo)
	{
		service.editUserPolicies(uname, policyNo);
		return "Policy added to User successfully";
	}
	
	@GetMapping(value="/fetchpolicy/{policyNo}",produces="application/json")
	public Policy fetchPolicy(@PathVariable String policyNo) {
		return service.findPolicy(policyNo);
	}
	
	@PutMapping(value="/renewpolicy/{uname},{policyNo},{expDate}",consumes="application/json")
	public String setRenewedDate(@PathVariable String uname,@PathVariable String policyNo,@PathVariable String expDate)
	{
		service.editRenewPolicy(uname, policyNo, expDate);
		return "New Expiry Date set for an User's Policy successfully";
	}
	
	@DeleteMapping("/del/{uname}")
	public String delUserPolicies(@PathVariable String uname) {
		service.deleteUserPolicies(uname);
		return "User policies deleted successfully";
	}
}

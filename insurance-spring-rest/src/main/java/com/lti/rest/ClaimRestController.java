package com.lti.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.entity.Claim;
import com.lti.service.ClaimService;

@CrossOrigin
@RestController
public class ClaimRestController {

	@Autowired
	private ClaimService service;
	
	@PostMapping(value="/addclaim",consumes="application/json")
	public String addClaim(@RequestBody Claim claim) {
		service.persistClaim(claim);
		return "Claim added successfully";
	}
	
	@PutMapping(value="/addpolicyclaim/{policyNo},{claimId}",consumes="application/json")
	public String addPolicyClaim(@PathVariable String policyNo,@PathVariable String claimId)
	{
		service.editPolicyClaim(policyNo, claimId);
		return "Claim added to Policy successfully";
	}
	
	@PutMapping(value="/adduserpolicyclaim/{uname},{policyNo},{claimId}",consumes="application/json")
	public String addUserPolicyClaim(@PathVariable String uname,@PathVariable String policyNo,@PathVariable String claimId)
	{
		service.editUserPolicyClaim(uname, policyNo, claimId);
		return "Claim added to an User's Policy successfully";
	}
	
	@PutMapping(value="/setuserclaim/{uname},{policyNo},{amount},{status}",consumes="application/json")
	public String setUserClaim(@PathVariable String uname,@PathVariable String policyNo,@PathVariable Double amount,@PathVariable String status)
	{
		service.editUserClaim(uname, policyNo, amount, status);
		return "Claim amount and Status set for an User's Policy successfully";
	}
	
	@DeleteMapping("/deluserclaim/{uname},{policyNo}")
	public String delUserClaim(@PathVariable String uname,@PathVariable String policyNo) {
		service.deleteUserClaim(uname, policyNo);
		return "User claim deleted successfully";
	}
}

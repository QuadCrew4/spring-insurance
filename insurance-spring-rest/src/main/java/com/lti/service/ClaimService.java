package com.lti.service;

import java.util.List;

import com.lti.entity.Claim;

public interface ClaimService {
	void persistClaim(Claim c);
	Claim findClaim(String claimId);
	List<Claim> listClaims();
	void editPolicyClaim(String policyNo, String claimId);
	void editUserPolicyClaim(String uname, String policyNo, String claimId);
	void editUserClaim(String uname, String policyNo, Double amount, String status);
	void deleteUserClaim(String uname, String policyNo);
	void deleteClaim(String claimId);
}

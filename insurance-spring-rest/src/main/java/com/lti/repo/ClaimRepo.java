package com.lti.repo;

import java.util.List;

import com.lti.entity.Claim;

public interface ClaimRepo {

	void saveClaim(Claim c);
	Claim fetchClaim(String cno);
	List<Claim> fetchClaims();
	void removeClaim(String claimId);
	void addPolicyClaim(String policyNo, String claimId);
	void addUserPolicyClaim(String uname,String policyNo, String claimId);
	void setUserClaim(String uname, String policyNo, Double amount, String status);
	void removeUserClaim(String uname, String policyNo);
}

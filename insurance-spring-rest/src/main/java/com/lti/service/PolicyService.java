package com.lti.service;

import java.util.List;

import com.lti.entity.Policy;

public interface PolicyService {

	void persistPolicy(Policy p);
	Policy findPolicy(String policyNo);
	List<Policy> listPolicies();
	void editUserPolicies(String uname, String policyNo);
	void editRenewPolicy(String uname, String policyNo,String expDate);
	void deleteUserPolicies(String uname);
	void deletePolicy(String policyNo);
}

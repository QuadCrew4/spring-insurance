package com.lti.repo;

import java.util.List;

import com.lti.entity.Policy;

public interface PolicyRepo {
	
	void savePolicy(Policy p);
	Policy fetchPolicy(String pno);
	List<Policy> fetchPolicies();
	void addUserPolicies(String uname, String policyNo);
	void removePolicy(String policyNo);
	void renewUserpolicy(String uname, String policyNo,String expDate);
	void removeUserPolicies(String uname);
}

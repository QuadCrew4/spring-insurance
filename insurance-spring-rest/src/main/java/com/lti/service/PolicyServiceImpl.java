package com.lti.service;

import java.util.List;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.entity.Policy;
import com.lti.repo.PolicyRepo;

@Service
public class PolicyServiceImpl implements PolicyService {
	
	@Autowired
	private PolicyRepo repo;
	
	@Transactional(value = TxType.REQUIRED)
	public void persistPolicy(Policy p) {
		repo.savePolicy(p);
	}

	public Policy findPolicy(String policyNo) {
		return repo.fetchPolicy(policyNo);
	}

	public List<Policy> listPolicies() {
		return repo.fetchPolicies();
	}

	@Transactional(value = TxType.REQUIRED)
	public void editUserPolicies(String uname, String policyNo) {
		repo.addUserPolicies(uname, policyNo);
	}

	@Transactional(value = TxType.REQUIRED)
	public void editRenewPolicy(String uname, String policyNo, String expDate) {
		repo.renewUserpolicy(uname, policyNo, expDate);
	}

	@Transactional(value = TxType.REQUIRED)
	public void deleteUserPolicies(String uname) {
		repo.removeUserPolicies(uname);
	}

	@Transactional(value = TxType.REQUIRED)
	public void deletePolicy(String policyNo) {
		repo.removePolicy(policyNo);
	}
}

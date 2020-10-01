package com.lti.service;

import java.util.List;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.entity.Claim;
import com.lti.repo.ClaimRepo;

@Service
public class ClaimServiceImpl implements ClaimService {
	
	@Autowired
	private ClaimRepo repo;

	@Transactional(value = TxType.REQUIRED)
	public void persistClaim(Claim c) {
		repo.saveClaim(c);
	}

	public Claim findClaim(String claimId) {
		return repo.fetchClaim(claimId);
	}

	public List<Claim> listClaims() {
		return repo.fetchClaims();
	}

	@Transactional(value = TxType.REQUIRED)
	public void editPolicyClaim(String policyNo,String claimId) {
		repo.addPolicyClaim(policyNo,claimId);
	}

	@Transactional(value = TxType.REQUIRED)
	public void editUserPolicyClaim(String uname, String policyNo, String claimId) {
		repo.addUserPolicyClaim(uname, policyNo, claimId);
	}

	@Transactional(value = TxType.REQUIRED)
	public void editUserClaim(String uname, String policyNo, Double amount, String status) {
		repo.setUserClaim(uname, policyNo, amount, status);
	}

	@Transactional(value = TxType.REQUIRED)
	public void deleteUserClaim(String uname, String policyNo) {
		repo.removeUserClaim(uname, policyNo);
	}

	@Transactional(value = TxType.REQUIRED)
	public void deleteClaim(String claimId) {
		repo.removeClaim(claimId);
	}
}

package com.lti.repo;

import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.stereotype.Repository;

import com.lti.entity.Claim;
import com.lti.entity.Policy;
import com.lti.entity.User;
import com.lti.entity.Vehicle;
import com.lti.pojo.Login;

@Repository
public class UserRepoImpl implements UserRepo {

	@PersistenceContext
	private EntityManager em;
	
	
	@Transactional(value = TxType.REQUIRED)
	public void saveUser(User u) {
		String pwd = u.getPassword();
		Encoder enc= Base64.getEncoder();
		u.setPassword(new String(enc.encode(pwd.getBytes())));
		em.persist(u);
	}
	
	@Transactional(value = TxType.REQUIRED)
	public void savePolicy(Policy p) {
		em.persist(p);
	}

	@Transactional(value = TxType.REQUIRED)
	public void saveClaim(Claim c) {
		em.persist(c);
	}
	
	@Transactional(value = TxType.REQUIRED)
	public void saveVehicle(Vehicle v) {
		em.persist(v);
	}

	public User fetchUser(String uname) {
		User u = em.find(User.class, uname);
		return u;
	}
	
	public List<User> fetchAll(){
		return em.createQuery("from User").getResultList();
	}
	
	public Policy fetchPolicy(String pno) {
		Policy p = em.find(Policy.class, pno);
		return p;
	}

	public Claim fetchClaim(String cno) {
		Claim c = em.find(Claim.class, cno);
		return c;
	}
	
	public Vehicle fetchVehicle(String regno) {
		Vehicle v = em.find(Vehicle.class, regno);
		return v;
	}
	
	public User authenticate(Login login) {
		String pwd = login.getPassword();
		Encoder enc= Base64.getEncoder();
		login.setPassword(new String(enc.encode(pwd.getBytes())));
		
		Query query = em.createNamedQuery("login");
		query.setParameter("uname", login.getUsername());
		query.setParameter("psw", login.getPassword());
		return (User)query.getSingleResult();
	}

	@Transactional(value = TxType.REQUIRED)
	public void updateUser(User u) {
		em.merge(u);
	}
	
	@Transactional(value = TxType.REQUIRED)
	public void renewInsurance(Policy p, String expDate)
	{
		p.setExpDate(expDate);
		em.merge(p);
	}
	
	@Transactional(value = TxType.REQUIRED)
	public void addUserPolicies(User u) {
		em.merge(u);
	}
	
	@Transactional(value = TxType.REQUIRED)
	public void addPolicyClaim(Policy p, Claim c) {
		p.setClaim(c);
		em.merge(p);
	}

	@Transactional(value = TxType.REQUIRED)
	public void addVehiclePolicy(Policy p, Vehicle v) {
		p.setVehicle(v);
		em.merge(p);		
	}
	
	@Transactional(value = TxType.REQUIRED)
	public void removeClaim(String claimId) {
		Claim c =em.find(Claim.class,claimId);
		em.remove(c);
	}
	
	@Transactional(value = TxType.REQUIRED)
	public void removePolicy(String policyNo) {
		Policy p =em.find(Policy.class,policyNo);
		em.remove(p);
	}
}

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

	public User fetchUser(String uname) {
		User u = em.find(User.class, uname);
		return u;
	}
	
	public List<User> fetchAll(){
		return em.createQuery("from User").getResultList();
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
	public void savePolicy(Policy p) {
		em.persist(p);
	}

	@Transactional(value = TxType.REQUIRED)
	public void saveClaim(Claim c) {
		em.persist(c);
	}

	@Transactional(value = TxType.REQUIRED)
	public void renewInsurance(Policy p, String expDate)
	{
		p.setExpDate(expDate);
		em.merge(p);
	}
	
	
	public Policy fetchUserPolicy(String pno) {
		Policy p = em.find(Policy.class, pno);
		return p;
	}

	public Claim fetchUserClaim(String cno) {
		Claim c = em.find(Claim.class, cno);
		return c;
	}
	
	@Transactional(value = TxType.REQUIRED)
	public void addUserPolicy(User u, Policy p) {
		u.setPolicy(p);
		em.merge(u);
	}
	
	@Transactional(value = TxType.REQUIRED)
	public void addUserClaim(User u, Claim c) {
		u.setClaim(c);
		em.merge(u);
	}
	
	@Transactional(value = TxType.REQUIRED)
	public void setUserClaim(Claim c,String stat, Double amt) {
		c.setStatus(stat);
		c.setAmount(amt);
		em.merge(c);
	}
	
	@Transactional(value = TxType.REQUIRED)
	public void removeUser(String uname) {
		em.remove( em.find(User.class, uname));
		
	}
	
	@Transactional(value = TxType.REQUIRED)
	public void removeUserClaim(String uname) {
		User u = em.find(User.class, uname);
		u.setClaim(null);
		em.merge(u);
	}
	
}

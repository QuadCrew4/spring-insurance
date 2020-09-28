package com.lti.repo;



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
		em.persist(u);
	}

	public User fetchUser(String uname) {
		User u = em.find(User.class, uname);
		return u;
	}

	public User authenticate(Login login) {
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

	public Policy fetchUserPolicy(String pno) {
		Policy p = em.find(Policy.class, pno);
		return p;
	}

	public Claim fetchUserClaim(String cno) {
		Claim c = em.find(Claim.class, cno);
		return c;
	}

}

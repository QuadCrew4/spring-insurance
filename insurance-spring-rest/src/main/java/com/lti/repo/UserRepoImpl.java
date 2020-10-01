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

	public void updateUser(User u) {
		em.merge(u);
	}
}


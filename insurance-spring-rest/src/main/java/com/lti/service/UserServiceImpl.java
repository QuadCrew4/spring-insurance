package com.lti.service;

import java.util.List;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.entity.User;
import com.lti.pojo.Login;
import com.lti.repo.UserRepo;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo repo;
	
	@Transactional(value = TxType.REQUIRED)
	public void persistUser(User u) {
		repo.saveUser(u);
	}

	public User findUser(String uname) {
		return repo.fetchUser(uname);
	}

	public List<User> list() {
		return repo.fetchAll();
	}

	@Transactional(value = TxType.REQUIRED)
	public void editUser(User u) {
		repo.updateUser(u);
	}

	public User validate(Login login) {
		User u =repo.authenticate(login);
		return u;
	}
}

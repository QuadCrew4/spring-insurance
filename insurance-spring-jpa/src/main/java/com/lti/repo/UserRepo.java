package com.lti.repo;

import java.util.List;

import com.lti.entity.User;

public interface UserRepo {
	
	void saveUser(User u);
	User fetchUser(int uid);
	List<User> fetchAll();
}

package com.lti.repo;

import java.util.List;


import com.lti.entity.User;
import com.lti.pojo.Login;

public interface UserRepo {
	
	void saveUser(User u);
	void updateUser(User u);
	User fetchUser(String uname);
	List<User> fetchAll();
	
	User authenticate(Login login);
}

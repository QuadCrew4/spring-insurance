package com.lti.repo;

import com.lti.entity.Claim;
import com.lti.entity.Policy;
import com.lti.entity.User;
import com.lti.pojo.Login;

public interface UserRepo {
	
	void saveUser(User u);
	void savePolicy(Policy p);
	void saveClaim(Claim c);
	void addUserPolicy(User u, Policy p);
	void addUserClaim(User u, Claim c);
	User fetchUser(String uname);
	Policy fetchUserPolicy(String pno);
	Claim fetchUserClaim(String cno);
	User authenticate(Login login);
	void removeUser(String uname);
}

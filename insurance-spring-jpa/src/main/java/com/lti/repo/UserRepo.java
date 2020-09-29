package com.lti.repo;

import java.util.List;

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
	void renewInsurance(Policy p, String expDate);
	User fetchUser(String uname);
	List<User> fetchAll();
	Policy fetchUserPolicy(String pno);
	Claim fetchUserClaim(String cno);
	User authenticate(Login login);
	void setUserClaim(Claim c,String stat, Double amt); 
	void removeUserClaim(String uname);
	void removeUser(String uname);
}

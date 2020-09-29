package com.lti.repo;

import java.util.List;

import com.lti.entity.Claim;
import com.lti.entity.Policy;
import com.lti.entity.User;
import com.lti.entity.Vehicle;
import com.lti.pojo.Login;

public interface UserRepo {
	
	void saveUser(User u);
	void savePolicy(Policy p);
	void saveClaim(Claim c);
	void saveVehicle(Vehicle v);
	void updateUser(User u);
	User fetchUser(String uname);
	List<User> fetchAll();
	Policy fetchPolicy(String pno);
	Claim fetchClaim(String cno);
	Vehicle fetchVehicle(String regno);
	User authenticate(Login login);
	void addUserPolicies(User u);
	void addPolicyClaim(Policy p, Claim c);
}

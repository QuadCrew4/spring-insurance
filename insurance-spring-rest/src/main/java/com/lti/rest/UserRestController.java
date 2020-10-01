package com.lti.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.lti.entity.User;
import com.lti.pojo.Login;
import com.lti.service.UserService;



@CrossOrigin
@RestController
public class UserRestController {
	
	@Autowired
	private UserService service;
	
	@PostMapping(value="/register",consumes="application/json")
	public String addUser(@RequestBody User user) {
		service.persistUser(user);
		return "User added successfully";
	}
	
	@GetMapping(value = "/login", produces = "application/json")
	public User login(@RequestParam("username") String username, @RequestParam("password") String password) {
		Login login = new Login(username, password);
		User user = service.validate(login);
		System.out.println(user.getUsername()+ "\t" + user.getPassword());
		return user;
	}
	
	@GetMapping(value="/list",produces="application/json")
	public List<User> listUsers() {
		return service.list();
	}
	
	@GetMapping(value="/fetch/{uname}",produces="application/json")
	public User fetchUser(@PathVariable String uname) {
		return service.findUser(uname);
	}
	
	@PutMapping(value="/edituser",consumes="application/json")
	public String editUser(@RequestBody User user) {
		service.editUser(user);
		return "User updated successfully";
	}
}

package com.manju.controller;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.manju.model.User;
import com.manju.model.UserDTO;
import com.manju.service.UserService;

@RestController
//@CrossOrigin(origins="*",allowedHeaders = "*")
public class UserController {
	@Autowired
	UserService userService;
	

	@PostMapping("/users/create")
	public String createUser(@RequestBody User user) {
	
		return userService.createUser(user);
	}
	

	@GetMapping("/users/byUserName/{username}")
	public UserDTO getUserbyusername(@PathVariable String username) {
		return userService.findUserByname(username);
	
	}
	
	@GetMapping("/users/getAll")
	public List<User> getAllUsers() {
		List<User> ulist=userService.getAllUsers();
		ulist=ulist.stream().map(user-> {user.setPassword(null); return user;}).collect(Collectors.toList());
		return ulist;
	}
	
	@GetMapping("/users/byEmail")
	public UserDTO getUserByEmail(@RequestParam String email) {
		return userService.findByUserEmail(email);
	}

}

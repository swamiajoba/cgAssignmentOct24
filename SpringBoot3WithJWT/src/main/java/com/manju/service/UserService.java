package com.manju.service;

import java.util.List;
import java.util.Set;

import com.manju.model.Account;
import com.manju.model.User;
import com.manju.model.UserDTO;

public interface UserService {
	//public UserDTO validateLogin(String username,String password);
	public String createUser(User user);
	public List<User> getAllUsers();
	public User findUserByname(String username);
	public Set<Account> getAccountsByUsername(String username);
}

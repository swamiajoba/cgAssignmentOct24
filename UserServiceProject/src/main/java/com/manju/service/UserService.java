package com.manju.service;

import java.util.List;
import com.manju.model.User;
import com.manju.model.UserDTO;

public interface UserService {
	public String createUser(User user);
	public List<User> getAllUsers();
	public UserDTO findUserByname(String username);
	public UserDTO findByUserEmail(String email);
}

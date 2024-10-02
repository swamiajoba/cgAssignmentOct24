package com.manju.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.manju.repo.UserDAO;
import com.manju.model.User;
import com.manju.model.UserDTO;
import com.manju.exception.RecordNotFoundException;

@Service("userService")   
public class UserServiceImpl implements UserService {
	@Autowired
	UserDAO userDAO;
	
	
	@Transactional(propagation = Propagation.REQUIRED)
	public String createUser(User user) {
		System.out.println(user);
		
		UserDTO u1=this.findUserByname(user.getUsername());
		if(u1!=null ) {
			throw new RecordNotFoundException("Duplicate User");
		}
		else {
		
		User u=userDAO.save(user);
		if(u==null) {
			throw new RecordNotFoundException("Records is not inserted in the database");
		}
		else {

			return "User Created Successfully";
		}
		}
	}

	@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return userDAO.findAll();
	}

	
	@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
	@Override
	public UserDTO findUserByname(String username) {
		// TODO Auto-generated method stub
		Optional<User> uop=userDAO.findById(username);
		if(uop!=null) {
		if(uop.isPresent()) {
			User user=uop.get();
			UserDTO userdto=new UserDTO(user.getUsername(),user.getEmail(),user.getCreatedAt());
			
			return userdto;
		}
		}
		else {
			throw new RecordNotFoundException("No user found by username := "+username);
		}
		return null;
	}
	
	@Override
	public UserDTO findByUserEmail(String email) {
		// TODO Auto-generated method stub
		User user=userDAO.findByEmail(email);
		if(user==null) {
			throw new RecordNotFoundException("No user found by email := "+ email);
		}
		UserDTO userdto=new UserDTO(user.getUsername(),user.getEmail(),user.getCreatedAt());
		return userdto;
	}

}

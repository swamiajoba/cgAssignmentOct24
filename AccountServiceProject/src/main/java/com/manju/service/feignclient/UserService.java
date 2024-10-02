package com.manju.service.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.manju.model.UserDTO;

@FeignClient("USER-SERVICE")
public interface UserService {
	 @GetMapping("/users/byEmail")
	  UserDTO getUserByEmail(@RequestParam("email") String email);
}

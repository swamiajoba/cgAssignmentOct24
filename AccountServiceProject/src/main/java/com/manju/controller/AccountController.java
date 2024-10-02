package com.manju.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;



import com.manju.repo.AccountDAO;
import com.manju.model.Account;

import com.manju.model.UserDTO;
import com.manju.exception.RecordNotFoundException;
import com.manju.service.AccountService;
import com.manju.service.feignclient.UserService;

import jakarta.validation.Valid;



@RestController
//@CrossOrigin(origins="*",allowedHeaders = "*")
public class AccountController {
	@Autowired
	AccountService accountService;
	
//	@Autowired
//	RestTemplate restTemplate;
	
		
		//http://localhost:8082/accounts
	@GetMapping("/accounts/getAll/v1") // versioning by URI
	//@GetMapping(value="/accounts/param",params="version=1") // versioning by Request Parameter
	//@GetMapping(value="/accounts/headers",headers = "X-API-VERSION=1") // versioning by Request header
	//@GetMapping(value="/accounts/produces",produces = "application/manju-company-app-v1+json") // versioning by produces in Accept header
	public List<Account> showAllAccV1()
	{
		
		return accountService.getAllAccounts();
	}
	
	@GetMapping("/accounts/getAll/v2") // versioning by URI
	//@GetMapping(value="/accounts/param",params="version=2") // versioning by Request Parameter
	//@GetMapping(value="/accounts/headers",headers = "X-API-VERSION=2") // versioning by Request header
	//@GetMapping(value="/accounts/produces",produces = "application/manju-company-app-v2+json") // versioning by produces in Accept header
	public List<Account> showAllAccV2(
		        @RequestParam(defaultValue = "0") Integer pageNo,
		        @RequestParam(defaultValue = "3") Integer pageSize,
		        @RequestParam(defaultValue = "aid") String sortBy)
	  {
		return accountService.getAllAccounts(pageNo, pageSize, sortBy);
	}
	
	 @PostMapping("/accounts/create")
	  public Account newAccount(@Valid @RequestBody Account newAccount) {
		 Account ac=accountService.saveAccount(newAccount);
		
		 return ac;
	  }
	 
	 // Inject Feign Client
	 @Autowired
	 UserService userService;
	 
	 // Call USER-SERVICE using Feign client
	 @GetMapping("/accounts/byUserEmail")
		public List<Account> getAccountsByUserName(@RequestParam String email) {
			UserDTO u=null;
			u=userService.getUserByEmail(email); // call USER-SERVICE
			List<Account> aclist= new ArrayList<>();
			if(u != null) {
				aclist=accountService.getAccountsByUsername(u.getUsername());
			}
			return aclist;
		}
	 
	 
	 //call USER-SERVICE Using RestTemplate
//	 @GetMapping("/accounts/byUserEmail")
//		public List<Account> getAccountsByUserName(@RequestParam String email) {
//			UserDTO u=null;
//			//u=restTemplate.getForObject("http://localhost:9091/users/byEmail?email="+email, UserDTO.class);
//			u=restTemplate.getForObject("http://USER-SERVICE/users/byEmail?email="+email, UserDTO.class);
//			List<Account> aclist= new ArrayList<>();
//			if(u != null) {
//				aclist=accountService.getAccountsByUsername(u.getUsername());
//			}
//			return aclist;
//		}
	 

	 
	 @PutMapping("/accounts/update/{id}")
	  public Account replaceAccount(@Valid @RequestBody Account newAccount, @PathVariable Integer id) {
		 
		 	Account ac=accountService.updateAccount(newAccount,id);
		    return ac;
	  }
	        
	 @DeleteMapping("/accounts/delete/{id}")
	  public void deleteAccount(@PathVariable Integer id) {
		 accountService.deleteAccount(id);
	  }
	 
	 @GetMapping("/accounts/byId/{id}")
	  public Account showByAid(@PathVariable Integer id)  {
		 Account ac=accountService.getAccountById(id);
		 return ac;
	  }


	 @GetMapping("/accounts/byBalance/{minbal}/{maxbal}")
	  public List<Account> showByBalanceRange(@PathVariable double minbal,@PathVariable double maxbal) throws RecordNotFoundException {

		 if(minbal<0 || maxbal<0 || minbal>maxbal) {
			 throw new InputMismatchException("Enter proper range of balance");
		 }
		 else {
			 List<Account> accs=accountService.getAccountsByBalanceRange(minbal, maxbal);
			  return accs;
		 }
	  }
	    
		 
	 @PutMapping("/accounts/transfermoney/{sourceaid}/{destinationaid}/{amount}")
	 public List<Account> transferMoney(@PathVariable int sourceaid, @PathVariable int destinationaid, @PathVariable double amount){
	
		 return accountService.transferMoney(sourceaid, destinationaid, amount);
	 }
	 
	
}

package com.manju.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import com.manju.advice.ErrorResponse;
import com.manju.exception.RecordNotFoundException;
import com.manju.model.Account;
import com.manju.model.Role;
import com.manju.model.User;
import com.manju.model.UserDTO;
import com.manju.repository.AccountDAO;
import com.manju.service.AccountService;
import com.manju.service.MyUserDetailsService;
import com.manju.service.UserDetailsImpl;
import com.manju.service.UserService;
import com.manju.util.JwtUtil;

import jakarta.validation.Valid;


@RestController
//@CrossOrigin(origins="http://localhost:4200",methods = {RequestMethod.GET})
//@CrossOrigin(origins="http://localhost:4200",allowedHeaders = "*")
@CrossOrigin(origins="*",allowedHeaders = "*")
public class AccountController {
	@Autowired
	AccountService accountService;

	@Autowired
	UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private MyUserDetailsService userDetailsService;

	@Autowired
	PasswordEncoder encoder;
	
//	@PostMapping("/accounts/login")
//	public UserDTO login(@RequestParam String username,@RequestParam String password) {
//		return userService.validateLogin(username, password);
//	}
	
	@PostMapping("/accounts/login")
	public UserDTO login(@RequestParam String username,@RequestParam String password)  {

		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(username, password)
		);
		
	
		

		final UserDetailsImpl userDetails = (UserDetailsImpl) userDetailsService
				.loadUserByUsername(username);

		final String jwt = jwtTokenUtil.generateToken(userDetails);
		
		UserDTO udto=new UserDTO();
		udto.setUsername(userDetails.getUsername());
		
		udto.setRole(Role.valueOf(userDetails.getAuthorities().iterator().next().getAuthority()));
		udto.setEmail(userDetails.getEmail());
		udto.setCreatedAt(userDetails.getCreatedAt());
		udto.setToken(jwt);
		return udto;
		
	}
	
	

//	@PostMapping("/accounts/createUser")
//	public String createUser(@RequestBody User user) {
//		String password=user.getPassword();
//		user.setPassword(encoder.encode(password));
//		return userService.createUser(user);
//	}
	
	@PostMapping("/accounts/createUser")
	public String createUser(@RequestParam String username,
						@RequestParam String password, 
						@RequestParam String email,
						@RequestParam String fullName,
						@RequestParam Role role,
						@RequestParam LocalDate createdAt, 
						@RequestPart("file") MultipartFile file) throws Exception {
		User newUser=new User();
		newUser.setUsername(username);
		newUser.setPassword(encoder.encode(password));
		newUser.setEmail(email);
		newUser.setFullName(fullName);
		newUser.setRole(role);
		newUser.setCreatedAt(createdAt);
		newUser.setPic(file.getBytes());

		return userService.createUser(newUser);
	}
	
	@GetMapping("/accounts/byUser/{username}")
	@PreAuthorize("permitAll()")
	public Set<Account> getAccountsByUserName(@PathVariable String username){
		Set<Account> accs=userService.getAccountsByUsername(username).stream().map(ac->{ac.getUser().setPassword(null);return ac;}).collect(Collectors.toSet());
		return accs;
	} 
	
	@GetMapping("/accounts/users")
	@PreAuthorize("hasAuthority('BANKEMPLOYEE')")
	public List<User> getAllUsers() {
		List<User> ulist=userService.getAllUsers();
		ulist=ulist.stream().map(user-> {user.setPassword(null); return user;}).collect(Collectors.toList());
		return ulist;
//		return userService.getAllUsers();
	}
	
	//http://localhost:8082/accounts
	@GetMapping("/accounts")
	@PreAuthorize("hasAuthority('BANKEMPLOYEE')")
	public List<Account> showAllAcc()
	{
		
		List<Account> alist=accountService.getAllAccounts().stream().map (ac->{if(ac.getUser()!=null)ac.getUser().setPassword(null);return ac;}).collect(Collectors.toList());
		System.out.println(accountService.getAllAccounts());
		return alist;
	}
	
	 @PostMapping("/accounts")
	 @PreAuthorize("hasAuthority('BANKEMPLOYEE')")
	  public Account newAccount(@Valid @RequestBody Account newAccount) {
		 Account ac=accountService.saveAccount(newAccount);
		 if(ac.getUser()!=null) {
			 ac.getUser().setPassword(null);
		 }
		 return ac;
	  }
	   
	 @GetMapping("/accounts/{id}")
	 @PreAuthorize("permitAll()")
	  public Account showByAid(@PathVariable Integer id)  {
		 Account ac=accountService.getAccountById(id);
		 if(ac.getUser()!=null) {
			 ac.getUser().setPassword(null);
		 }
		 return ac;
	  }

	 @GetMapping("/accountsByCust/{cust}")
	 @PreAuthorize("hasAuthority('BANKEMPLOYEE')")
	  public List<Account> showByCustomer(@PathVariable String cust)  {

		 if(cust==null || cust.isEmpty()) {
			 throw new InputMismatchException("Customer can not be Empty");
		 }
		 else {
			 List<Account> accs=accountService.getAccountsByCustomer(cust).stream().map (ac->{if(ac.getUser()!=null)ac.getUser().setPassword(null);return ac;}).collect(Collectors.toList());;
			 System.out.println(accs);
			 return accs;
		 }
	  }

	 @GetMapping("/accountsByBalance/{minbal}/{maxbal}")
	 @PreAuthorize("hasAuthority('BANKEMPLOYEE')")
	  public List<Account> showByBalanceRange(@PathVariable double minbal,@PathVariable double maxbal) throws RecordNotFoundException {

		 if(minbal<0 || maxbal<0 || minbal>maxbal) {
			 throw new InputMismatchException("Enter proper range of balance");
		 }
		 else {
			 List<Account> accs=accountService.getAccountsByBalanceRange(minbal, maxbal).stream().map (ac->{if(ac.getUser()!=null)ac.getUser().setPassword(null);return ac;}).collect(Collectors.toList());
			  return accs;
		 }
	  }
	    
	 @PutMapping("/accounts/{id}")
	 @PreAuthorize("permitAll()")
	  public Account replaceAccount(@Valid @RequestBody Account newAccount, @PathVariable Integer id) {
		 
		 	Account ac=accountService.updateAccount(newAccount,id);
		 	if(ac.getUser() !=null) {
		 		ac.getUser().setPassword(null);
		 	}
	        return ac;
	  }
	        
	 @DeleteMapping("/accounts/{id}")
	 @PreAuthorize("hasAuthority('BANKEMPLOYEE')")
	  public void deleteAccount(@PathVariable Integer id) {
		 accountService.deleteAccount(id);
	  }
	 
	 
	 // local to the RestController
	 @ExceptionHandler(InputMismatchException.class)
	    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
	        List<String> details = new ArrayList<>();
	        details.add(ex.getLocalizedMessage());
	        ErrorResponse error = new ErrorResponse("Server Error From controller", details);
	        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
}

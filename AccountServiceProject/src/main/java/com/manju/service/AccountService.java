package com.manju.service;

import java.util.List;

import com.manju.model.Account;
import com.manju.exception.RecordNotFoundException;

public interface AccountService {
	public  Account saveAccount(Account ac);
	public Account updateAccount(Account newAccount,int id);
	public void deleteAccount(int aid);
	
	public Account getAccountById(int aid);
	
	public List<Account> getAllAccounts();
	

	public List<Account> getAccountsByBalanceRange(double minbal,double maxbal) ;
	
	public List<Account> transferMoney(int sourceaid,int destinationaid,double amount) ;
	
	 public List<Account> getAllAccounts(Integer pageNo, Integer pageSize, String sortBy);
	 
	 public List<Account> getAccountsByUsername(String username);
}

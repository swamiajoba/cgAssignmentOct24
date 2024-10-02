package com.manju.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.manju.repo.AccountDAO;
import com.manju.model.Account;
import com.manju.exception.RecordNotFoundException;

@Service("accountService")
public class AccountServiceImpl implements AccountService{

	@Autowired
	AccountDAO accountDAO;

	
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public Account saveAccount(Account ac) {
			return accountDAO.save(ac);
	}
	
	//paging and sorting
		 public List<Account> getAllAccounts(Integer pageNo, Integer pageSize, String sortBy)
		    {
		        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		 
		        Page<Account> pagedResult = accountDAO.findAll(paging);
		         
		        if(pagedResult.hasContent()) {
		            return pagedResult.getContent();
		        } else {
		            return new ArrayList<Account>();
		        }
		    }
		 

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public Account updateAccount(Account newAccount,int id) {
		// TODO Auto-generated method stub
		 Optional<Account> optac=accountDAO.findById(id);
		 if(optac==null) {
				throw new RecordNotFoundException("Could not find Account with id "+id);
			}
		 Account ac=null;
		 if(optac.isPresent()) {
			 ac=optac.get();
			 ac.setBalance(newAccount.getBalance());
			 ac.setUsername(newAccount.getUsername());
		 }
		
		 
		 return accountDAO.save(ac);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void deleteAccount(int aid) {
		// TODO Auto-generated method stub
		accountDAO.deleteById(aid);
	}

	@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
	@Override
	public List<Account> getAllAccounts() {
		// TODO Auto-generated method stub
		return accountDAO.findAll();
	}

	@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
	@Override
	public Account getAccountById(int aid)  {
	
		return accountDAO.findById(aid)
				.orElseThrow(()-> new RecordNotFoundException("Could not find Account with id "+aid));
		
	}


	@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
	@Override
	public List<Account> getAccountsByBalanceRange(double minbal, double maxbal)  {
		List<Account> alist=accountDAO.findByBalanceBetween(minbal, maxbal);
		if(alist.size()==0) {
			throw new RecordNotFoundException("Could not find any Account in balance range of  "+minbal +" and "+maxbal);
		}
		else
			return alist;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public List<Account> transferMoney(int sourceaid, int destinationaid, double amount) {
		// TODO Auto-generated method stub
		
		Account sourceAc=this.getAccountById(sourceaid) ;
		Account destinationAc=this.getAccountById(destinationaid);
		if(sourceAc!=null && destinationAc !=null) {
			sourceAc.setBalance(sourceAc.getBalance()-amount);
			destinationAc.setBalance(destinationAc.getBalance()+amount);
			this.updateAccount(sourceAc, sourceaid);
			this.updateAccount(destinationAc, destinationaid);
		}
		return this.getAllAccounts();
	}

	@Override
	public List<Account> getAccountsByUsername(String username) {
		// TODO Auto-generated method stub
		return accountDAO.findByUsername(username);
	}
}

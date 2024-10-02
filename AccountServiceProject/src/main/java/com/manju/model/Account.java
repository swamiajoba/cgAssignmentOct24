package com.manju.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;

@Entity
@Table(name="accounts")
public class Account implements java.io.Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) 
	private int aid;
	

	
	@Min(value=2000,message="Balance should be minimum 2000")
	private double balance;
	
	private String username;
	
	public Account() {
		// TODO Auto-generated constructor stub
	}

	


	public int getAid() {
		return aid;
	}
	
	public void setAid(int aid) {
		this.aid = aid;
	}


	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}

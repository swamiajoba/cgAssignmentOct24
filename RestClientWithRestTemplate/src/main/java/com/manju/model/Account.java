package com.manju.model;

public class Account implements java.io.Serializable{

	private int aid;
	
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




	@Override
	public String toString() {
		return "Account [aid=" + aid + ", balance=" + balance + ", username=" + username + "]";
	}
	
}

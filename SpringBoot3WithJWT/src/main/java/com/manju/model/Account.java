package com.manju.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;

@Entity
@Table(name="account2")
//Owner Side
public class Account implements java.io.Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
//	@GeneratedValue(strategy=GenerationType.SEQUENCE, )
	
	private int aid;
	

	
	@Min(value=2000,message="Balance should be minimum 2000")
	private double balance;

	/*
		The many side of many-to-one bidirectional relationships must not define 
		the mappedBy element. The many side is always the owning side 
		of the relationship.
	*/
	@ManyToOne
	@JoinColumn(name="username")
	private User user;
	
	
	public Account() {
		// TODO Auto-generated constructor stub
	}



	public Account(int aid, @Min(value = 2000, message = "Balance should be minimum 2000") double balance, User user) {
	super();
	this.aid = aid;
	this.balance = balance;
	this.user = user;
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

	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Account [aid=" + aid + ", balance=" + balance + ", user=" + user + "]";
	}

	
}

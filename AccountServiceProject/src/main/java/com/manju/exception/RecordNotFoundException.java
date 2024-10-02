package com.manju.exception;

public class RecordNotFoundException extends RuntimeException{
	public RecordNotFoundException() {
		// TODO Auto-generated constructor stub
	}
	
	public RecordNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "AccountNotFoundException ["+getMessage()+"]";
	}

}

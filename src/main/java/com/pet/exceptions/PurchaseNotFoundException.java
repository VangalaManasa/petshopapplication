package com.pet.exceptions;

public class PurchaseNotFoundException extends Exception {
	
	private static final long serialVersionUID = 1L;
	String message;
	public PurchaseNotFoundException(String message) {
		super();
		this.message = message;
	}
	public PurchaseNotFoundException() {
		super();
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	

}

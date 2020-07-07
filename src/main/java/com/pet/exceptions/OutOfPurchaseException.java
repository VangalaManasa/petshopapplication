package com.pet.exceptions;

public class OutOfPurchaseException extends Exception {
	private static final long serialVersionUID = 1L;
	String message;
	public OutOfPurchaseException(String message) {
		super();
		this.message = message;
	}
	public OutOfPurchaseException() {
		super();
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}

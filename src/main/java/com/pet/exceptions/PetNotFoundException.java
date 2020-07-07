package com.pet.exceptions;

public class PetNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;
	String message;
	public PetNotFoundException(String message) {
		super();
		this.message = message;
	}
	public PetNotFoundException() {
		super();
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	

}

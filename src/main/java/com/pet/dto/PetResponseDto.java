package com.pet.dto;

public class PetResponseDto {
	
	private int petId;
	private String petName;
	private double petPrice;
	private String petColour;
	private String message;
	private Integer statusCode;
	public int getPetId() {
		return petId;
	}
	public void setPetId(int petId) {
		this.petId = petId;
	}
	public String getPetName() {
		return petName;
	}
	public void setPetName(String petName) {
		this.petName = petName;
	}
	public double getPetPrice() {
		return petPrice;
	}
	public void setPetPrice(double petPrice) {
		this.petPrice = petPrice;
	}
	public String getPetColour() {
		return petColour;
	}
	public void setPetColour(String petColour) {
		this.petColour = petColour;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Integer getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}
	
	

}

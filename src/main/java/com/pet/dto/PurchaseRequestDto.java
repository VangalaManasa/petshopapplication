package com.pet.dto;

public class PurchaseRequestDto {
	private int userId;
	private int petId;
	private int noOfPets;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getPetId() {
		return petId;
	}
	public void setPetId(int petId) {
		this.petId = petId;
	}
	public int getNoOfPets() {
		return noOfPets;
	}
	public void setNoOfPets(int noOfPets) {
		this.noOfPets = noOfPets;
	}
	

}

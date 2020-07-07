package com.pet.service;

import java.util.List;

import com.pet.dto.PetsPurchaseDto;
import com.pet.dto.PurchaseRequestDto;
import com.pet.dto.PurchaseResponseDto;
import com.pet.exceptions.OutOfPurchaseException;
import com.pet.exceptions.PurchaseNotFoundException;

public interface PurchaseService {

	PurchaseResponseDto buyPet(PurchaseRequestDto purchaseRequestDto) throws OutOfPurchaseException;

	List<PetsPurchaseDto> getAllPetsByUserId(int userId) throws PurchaseNotFoundException;

}

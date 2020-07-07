package com.pet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pet.dto.PetsPurchaseDto;
import com.pet.dto.PurchaseRequestDto;
import com.pet.dto.PurchaseResponseDto;

import com.pet.exceptions.PurchaseNotFoundException;
import com.pet.service.PurchaseService;

@RestController
public class PurchaseController {
	@Autowired
	PurchaseService purchaseService;
	
	@GetMapping("/users/{userId}/purchases")
	public List<PetsPurchaseDto> getAllPetsByUserId(@PathVariable int userId) throws PurchaseNotFoundException
	{
		try {
		return purchaseService.getAllPetsByUserId(userId);
		}catch(PurchaseNotFoundException e)
		{
			throw e ;
		}
	}
	
	@PostMapping("/purchases")
	public PurchaseResponseDto buyPet(@RequestBody PurchaseRequestDto purchaseRequestDto)
	{
		return purchaseService.buyPet(purchaseRequestDto);
	}
	
	@ExceptionHandler(PurchaseNotFoundException.class)
	public ResponseEntity<String> exceptionHandler(PurchaseNotFoundException exception) {
		return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);

	}

}

package com.pet.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
import com.pet.exceptions.OutOfPurchaseException;
import com.pet.exceptions.PurchaseNotFoundException;
import com.pet.service.PurchaseService;

/**
 * @author V.Manasa
 *
 */
@RestController
public class PurchaseController {
	private static Log logger = LogFactory.getLog(PurchaseController.class);
	@Autowired
	PurchaseService purchaseService;
	
	/**
	 * @param userId
	 * @return petspurchaseDto
	 * @throws PurchaseNotFoundException
	 */
	@GetMapping("/users/{userId}/purchases")
	public List<PetsPurchaseDto> getAllPetsByUserId(@PathVariable int userId) throws PurchaseNotFoundException
	{
		logger.info("inside getallpets by user id method");
		try {
		return purchaseService.getAllPetsByUserId(userId);
		}catch(PurchaseNotFoundException e)
		{
			throw e ;
		}
	}
	
	/**
	 * @param purchaseRequestDto
	 * @return purchaseResponseDto
	 * @throws OutOfPurchaseException 
	 */
	@PostMapping("/purchases")
	public PurchaseResponseDto buyPet(@RequestBody PurchaseRequestDto purchaseRequestDto) throws OutOfPurchaseException
	{
		logger.info("inside buying pet method");
		try {
		return purchaseService.buyPet(purchaseRequestDto);
		}catch(OutOfPurchaseException e)
		{
			throw e ;
		}
	}
	
	@ExceptionHandler(PurchaseNotFoundException.class)
	public ResponseEntity<String> exceptionHandler(PurchaseNotFoundException exception) {
		return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);

	}
	@ExceptionHandler(OutOfPurchaseException.class)
	public ResponseEntity<String> exceptionHandler(OutOfPurchaseException exception) {
		return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);

	}

	
	

}

package com.pet.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pet.dto.PetResponseDto;
import com.pet.exceptions.PetNotFoundException;
import com.pet.service.PetService;

/**
 * @author V.Manasa
 *
 */
@RestController
public class PetController {
	private static Log logger = LogFactory.getLog(PurchaseController.class);

	@Autowired
	PetService petService;

	/**
	 * @param petName
	 * @return petResponseDto
	 * @throws PetNotFoundException
	 */
	@GetMapping("/pets/name")
	public List<PetResponseDto> getPetByName(@RequestParam("petName") String petName) throws PetNotFoundException {
		logger.info("inside searching for pets menthod");
		try {
			return petService.getPetByName(petName);
		} catch (PetNotFoundException e) {
			throw e;
		}
	}

	@ExceptionHandler(PetNotFoundException.class)
	public ResponseEntity<String> exceptionHandler(PetNotFoundException exception) {
		return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);

	}

}

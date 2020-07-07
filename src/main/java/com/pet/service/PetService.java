package com.pet.service;

import java.util.List;

import com.pet.dto.PetResponseDto;
import com.pet.exceptions.PetNotFoundException;



public interface PetService {

	List<PetResponseDto> getPetByName(String petName) throws PetNotFoundException;

}

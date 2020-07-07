package com.pet.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.pet.dao.PetDao;
import com.pet.dto.PetResponseDto;
import com.pet.exceptions.PetNotFoundException;
import com.pet.model.Pet;
import com.pet.service.PetService;
@Service
public class PetServiceImpl implements PetService {
	private static Log logger = LogFactory.getLog(PetServiceImpl.class);
	
	@Autowired
	PetDao petDao;

	@Override
	public List<PetResponseDto> getPetByName(String petName) throws PetNotFoundException {
		logger.info("inside serach petsby petname method");
		Optional<List<Pet>> petsOptional=petDao.getPetByPetName(petName);
		if(petsOptional.isPresent())
		{
			return petsOptional.get().stream().map(pet -> addResponseDtoList(pet)).collect(Collectors.toList());
		}
		List<PetResponseDto> responseDtos=new  ArrayList<PetResponseDto>();
		responseDtos.add(new PetResponseDto());
		throw new PetNotFoundException("There exists no pet with pet name: " +petName);
	}
	
	private PetResponseDto addResponseDtoList(Pet pet) {
		PetResponseDto responseDto=new PetResponseDto();
		responseDto.setMessage("details of the pets");
		responseDto.setStatusCode(HttpStatus.OK.value());
		BeanUtils.copyProperties(pet, responseDto);
		
		return responseDto;
	}
	

}

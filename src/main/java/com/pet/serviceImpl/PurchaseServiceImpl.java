package com.pet.serviceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.pet.dao.PetDao;
import com.pet.dao.PurchaseDao;
import com.pet.dto.PetResponseDto;
import com.pet.dto.PetsPurchaseDto;
import com.pet.dto.PurchaseRequestDto;
import com.pet.dto.PurchaseResponseDto;
import com.pet.exceptions.OutOfPurchaseException;
import com.pet.exceptions.PurchaseNotFoundException;
import com.pet.model.Pet;
import com.pet.model.Purchase;
import com.pet.service.PurchaseService;
@Service
public class PurchaseServiceImpl implements PurchaseService {
	@Autowired
	PurchaseDao purchaseDao;
	@Autowired
	PetDao petDao;

	@Override
	public PurchaseResponseDto buyPet(PurchaseRequestDto purchaseRequestDto) throws OutOfPurchaseException {
		Purchase purchase=new Purchase();
		if(purchaseRequestDto.getNoOfPets()>4)
		{
			throw new OutOfPurchaseException("user can purchase  only 3");
		}
		BeanUtils.copyProperties(purchaseRequestDto, purchase);
		purchase.setDate(LocalDateTime.now());
		purchase.setTotalCost(petDao.findPetPriceByPetId(purchaseRequestDto.getPetId())*purchaseRequestDto.getNoOfPets());
		Purchase purchase2=purchaseDao.save(purchase);
		
		PurchaseResponseDto purchaseResponseDto=new PurchaseResponseDto();
		BeanUtils.copyProperties(purchase2, purchaseResponseDto);
		purchaseResponseDto.setPetName(petDao.findPetNameByPetId(purchaseRequestDto.getPetId()));
		purchaseResponseDto.setMessage("pet purchased successfully");
		purchaseResponseDto.setStatusCode(HttpStatus.OK.value());
		return purchaseResponseDto;
		
	}

	@Override
	public List<PetsPurchaseDto> getAllPetsByUserId(int userId) throws PurchaseNotFoundException {
		Optional<List<Purchase>> purchaseList=purchaseDao.findAllByUserId(userId);
		if(purchaseList.isPresent())
		{
			return purchaseList.get().stream().map(purchase -> addResponseDtoList(purchase)).collect(Collectors.toList());
		}
		List<PetResponseDto> responseDtos=new  ArrayList<PetResponseDto>();
		responseDtos.add(new PetResponseDto());
		throw new PurchaseNotFoundException("user not done any purchase with particular userid: " +userId);
	}
	
	private PetsPurchaseDto addResponseDtoList(Purchase purchase) {
		PetsPurchaseDto responseDto=new PetsPurchaseDto();
		Optional<Pet> pets=petDao.findById(purchase.getPetId());
		if(!pets.isPresent()) return null;
		responseDto.setPetName(pets.get().getPetName());
		responseDto.setPetPrice(pets.get().getPetPrice());
		responseDto.setPetColour(pets.get().getPetColour());
		responseDto.setMessage("details of the purchased pets");
		responseDto.setStatusCode(HttpStatus.OK.value());
		BeanUtils.copyProperties(purchase, responseDto);
		
		return responseDto;
	}
	
	
	
	

		
	

}

package com.pet.conrollertest;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pet.controller.PetController;
import com.pet.dao.PetDao;
import com.pet.dto.PetResponseDto;
import com.pet.exceptions.PetNotFoundException;
import com.pet.model.Pet;
import com.pet.service.PetService;
import com.pet.serviceImpl.PetServiceImpl;

@ExtendWith(MockitoExtension.class)
public class PetControllerTest {
	@Mock
	PetService petService;
	@Mock
	PetDao petDao;
	MockMvc mockMvc;

	ObjectMapper objectMapper;
	@InjectMocks
	PetController petController;
	@InjectMocks
    PetServiceImpl petServiceImpl;
	Pet pet;

	@BeforeEach
	public void setUp() {
		objectMapper = new ObjectMapper();
		mockMvc = MockMvcBuilders.standaloneSetup(petController).build();

	}
	
	 @Test
     public void getPetsByPetName() throws PetNotFoundException {
         
         List<Pet> petsList = new ArrayList<>();
         PetResponseDto petResponseDto = new PetResponseDto();
         
       
         petResponseDto.setMessage("details of the pets");
 		petResponseDto.setPetColour("white");
 		petResponseDto.setPetId(1);
 		petResponseDto.setPetName("dog");
 		petResponseDto.setPetPrice(2000);
 		petResponseDto.setStatusCode(200);
         
         
         when(petDao.getPetByPetName("dog")).thenReturn(Optional.ofNullable(petsList));
         
         petServiceImpl.getPetByName("dog");
         
         verify(petDao).getPetByPetName("dog");
     
     }
	 
	 @Test
     public void getPetsByPetName1() throws PetNotFoundException {
         
         List<Pet> petsList = new ArrayList<>();
         PetResponseDto petResponseDto = new PetResponseDto();
         
       
         petResponseDto.setMessage("details of the pets");
 		petResponseDto.setPetColour("white");
 		petResponseDto.setPetId(1);
 		petResponseDto.setPetName("dog");
 		petResponseDto.setPetPrice(2000);
 		petResponseDto.setStatusCode(200);
         
         
         when(petDao.getPetByPetName("dog")).thenReturn(Optional.of(petsList));
         
         petServiceImpl.getPetByName("dog");
         
         verify(petDao).getPetByPetName("dog");
     
     }
	 
	 @Test
     public void getPetsByPetName2() throws PetNotFoundException {
         
         List<Pet> petsList = new ArrayList<>();
         PetResponseDto petResponseDto = new PetResponseDto();
         
       
         petResponseDto.setMessage("details of the pets");
 		petResponseDto.setPetColour("white");
 		petResponseDto.setPetId(1);
 		petResponseDto.setPetName("dog");
 		petResponseDto.setPetPrice(2000);
 		petResponseDto.setStatusCode(200);
         
         
 		  PetNotFoundException exception = assertThrows(PetNotFoundException.class, () -> {
              petServiceImpl.getPetByName("dog");
          });
           String expectedMessage = "There exists no pet with pet name";
              String actualMessage = exception.getMessage();
              assertTrue(actualMessage.contains(expectedMessage));
     
     }



}

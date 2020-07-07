package com.pet.conrollertest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.LinkedHashMap;

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
import com.pet.controller.PurchaseController;
import com.pet.dto.PurchaseRequestDto;
import com.pet.dto.PurchaseResponseDto;
import com.pet.service.PurchaseService;

@ExtendWith(MockitoExtension.class)
public class PurchaseControllerTest {
	@Mock
	PurchaseService purchaseService;

	MockMvc mockMvc;

	ObjectMapper objectMapper;
	@InjectMocks
	PurchaseController purchaseController;

	PurchaseRequestDto purchaseRequestDto;

	@BeforeEach
	public void setUp() {
		objectMapper = new ObjectMapper();
		mockMvc = MockMvcBuilders.standaloneSetup(purchaseController).build();

		purchaseRequestDto = new PurchaseRequestDto();
		purchaseRequestDto.setNoOfPets(2);
		purchaseRequestDto.setPetId(1);
		purchaseRequestDto.setUserId(1);

	}

	@Test
	public void buyPet() throws Exception {
		PurchaseResponseDto purchaseResponseDto = new PurchaseResponseDto();
		purchaseResponseDto.setMessage("pet purchased successfully");
		purchaseResponseDto.setPetName("cat");
		purchaseResponseDto.setTotalCost(6000);
		purchaseResponseDto.setStatusCode(200);

		// given
		when(purchaseService.buyPet(any(PurchaseRequestDto.class))).thenReturn(purchaseResponseDto);

		mockMvc.perform(post("/purchases").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(purchaseRequestDto))).andExpect(status().isOk())
				.andExpect(jsonPath("$", Matchers.any(LinkedHashMap.class)));

		verify(purchaseService).buyPet(any(PurchaseRequestDto.class));
	}

}

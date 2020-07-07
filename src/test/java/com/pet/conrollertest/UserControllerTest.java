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
import com.pet.controller.UserController;
import com.pet.dto.LoginRequestDto;
import com.pet.dto.LoginResponseDto;
import com.pet.service.UserService;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

	@Mock
    UserService userService;

	MockMvc mockMvc;
    ObjectMapper objectMapper;

    @InjectMocks
    UserController userController;

    LoginRequestDto loginRequestDto;
    LoginResponseDto loginResponseDto;
    
    

    @BeforeEach
    public void setUp() {
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        
        
    }

    	@Test
        public void userLoginTest() throws Exception
        {
    		LoginRequestDto loginRequestDto = new LoginRequestDto();
    		
    		loginRequestDto.setPassword("1234");
    		loginRequestDto.setUserName("manasa");
    		
           LoginResponseDto loginResponseDto = new LoginResponseDto();
           
           loginResponseDto.setMessage("user logged in successfully!!");
           loginResponseDto.setStatusCode(200);
           
            when(userService.userLogin(any(LoginRequestDto.class))).thenReturn(loginResponseDto);
            mockMvc.perform(post("/users/login").contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(objectMapper.writeValueAsString(loginRequestDto)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", Matchers.any(LinkedHashMap.class)));
    
            verify(userService).userLogin(any(LoginRequestDto.class));
        }
}

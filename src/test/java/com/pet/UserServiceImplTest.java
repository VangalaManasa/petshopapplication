package com.pet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pet.dao.UserDao;
import com.pet.dto.LoginRequestDto;
import com.pet.dto.LoginResponseDto;
import com.pet.exceptions.InvalidCredentialsException;
import com.pet.model.User;
import com.pet.service.UserService;
import com.pet.serviceImpl.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

	@Mock
	UserDao userDao;

	@InjectMocks
	UserServiceImpl userServiceImpl;

	LoginResponseDto loginResponseDto;

	@Mock
	UserService userService;

	@BeforeEach
	public void setUp() {

	}
	
	@Test
	public void userLoginTest1() {
		LoginRequestDto loginRequestDto = new LoginRequestDto();
		loginRequestDto.setPassword("1234");
		loginRequestDto.setUserName("manasa");

		loginResponseDto = new LoginResponseDto();
		loginResponseDto.setUserId(1);
		loginResponseDto.setMessage("Employee logged in");
		loginResponseDto.setStatusCode(200);

		User user = new User();
		

		assertThat(!(loginRequestDto.getUserName().isEmpty() && loginRequestDto.getPassword().isEmpty())).isTrue();

		when(userDao.findByUserNameAndPassword("manasa", "1234")).thenReturn(Optional.of(user));
		userServiceImpl.userLogin(loginRequestDto);
		verify(userDao).findByUserNameAndPassword("manasa", "1234");

	}
	
	@Test
	public void userLoginTest2() {
		LoginRequestDto loginRequestDto = new LoginRequestDto();
		loginRequestDto.setPassword("1234");
		loginRequestDto.setUserName("manasa");

		loginResponseDto = new LoginResponseDto();
		loginResponseDto.setUserId(1);
		loginResponseDto.setMessage("Employee logged in");
		loginResponseDto.setStatusCode(200);

		User user = new User();
		

		assertThat(!(loginRequestDto.getUserName().isEmpty() && loginRequestDto.getPassword().isEmpty())).isTrue();

		when(userDao.findByUserNameAndPassword("manasa", "1234")).thenReturn(Optional.ofNullable(user));
		userServiceImpl.userLogin(loginRequestDto);
		verify(userDao).findByUserNameAndPassword("manasa", "1234");

	}
	
	@Test
	public void userLoginTest3() {
		LoginRequestDto loginRequestDto = new LoginRequestDto();
		loginRequestDto.setPassword("1234");
		loginRequestDto.setUserName("manasa");

		loginResponseDto = new LoginResponseDto();
		loginResponseDto.setUserId(1);
		loginResponseDto.setMessage("Employee logged in");
		loginResponseDto.setStatusCode(200);

		
		
		 InvalidCredentialsException exception = assertThrows(InvalidCredentialsException.class, () -> {
		        userServiceImpl.userLogin(loginRequestDto);
		    });
		 
		    String expectedMessage = "User not found";
		    String actualMessage = exception.getMessage();
		    assertTrue(actualMessage.contains(expectedMessage));
	}
}

package com.pet.serviceImpl;

import java.util.Optional;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.pet.dao.UserDao;
import com.pet.dto.LoginRequestDto;
import com.pet.dto.LoginResponseDto;
import com.pet.exceptions.InvalidCredentialsException;
import com.pet.model.User;
import com.pet.service.UserService;
@Service
public class UserServiceImpl implements UserService{
	private static Log logger = LogFactory.getLog(UserServiceImpl.class);
	@Autowired
	UserDao userDao;

	@Override
	public LoginResponseDto userLogin(@Valid LoginRequestDto loginRequestDto) {
		LoginResponseDto loginResponseDto = new LoginResponseDto();
		logger.info("Validating the request");
		Optional<User> userOptional = userDao.findByUserNameAndPassword(loginRequestDto.getUserName(),
				loginRequestDto.getPassword());
		logger.info("verifying the existence of User");

		if (!userOptional.isPresent()) 
		{
			throw new InvalidCredentialsException("User not found ");
			
		}
		loginResponseDto.setUserId(userOptional.get().getUserId());
		loginResponseDto.setMessage("User logged in Successfully");
		loginResponseDto.setStatusCode(HttpStatus.OK.value());
		return loginResponseDto;

	}
	

}

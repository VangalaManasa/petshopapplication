package com.pet.controller;



import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pet.dto.LoginRequestDto;
import com.pet.dto.LoginResponseDto;
import com.pet.service.UserService;




/**
 * @author V.Manasa
 *
 */
@RestController
public class UserController {
	private static Log logger = LogFactory.getLog(UserController.class);

	@Autowired
	UserService userService;
	
	
	
	/**
	 * @param loginRequestDto
	 * @return
	 */
	@PostMapping("users/login")
	public ResponseEntity<LoginResponseDto> userLogin(@Valid @RequestBody LoginRequestDto loginRequestDto) {
		logger.info("inside user login method");
		return new ResponseEntity<>(userService.userLogin(loginRequestDto), HttpStatus.OK);
	}

}

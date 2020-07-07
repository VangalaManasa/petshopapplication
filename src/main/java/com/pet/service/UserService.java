package com.pet.service;

import javax.validation.Valid;

import com.pet.dto.LoginRequestDto;
import com.pet.dto.LoginResponseDto;

public interface UserService {

	LoginResponseDto userLogin(@Valid LoginRequestDto loginRequestDto);

}

package com.emt.lab.service;

import com.emt.lab.dto.AuthRequestDto;
import com.emt.lab.dto.LoginResponseDto;

public interface AuthService {

    LoginResponseDto login(AuthRequestDto authRequestDto);

}

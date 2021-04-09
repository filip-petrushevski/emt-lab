package com.emt.lab.service.impl;

import com.emt.lab.dto.AuthRequestDto;
import com.emt.lab.dto.LoginResponseDto;
import com.emt.lab.exception.BadRequestException;
import com.emt.lab.repository.UserRepository;
import com.emt.lab.utils.JwtUtil;
import com.emt.lab.service.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthServiceImpl(UserRepository userRepository, AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public LoginResponseDto login(AuthRequestDto authRequestDto) {
        final String username = authRequestDto.getUsername();
        final String password = authRequestDto.getPassword();
        final UserDetails userDetails;
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));
            userDetails = userRepository.findByUsername(username).get();
        } catch (RuntimeException e) {
            throw new BadRequestException("Login failed");
        }

        final String jwt = jwtUtil.generateToken(userDetails);
        return new LoginResponseDto("Bearer", jwt);

    }

}

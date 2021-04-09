package com.emt.lab.api;

import com.emt.lab.dto.AuthRequestDto;
import com.emt.lab.dto.LoginResponseDto;
import com.emt.lab.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    public LoginResponseDto login(@RequestBody AuthRequestDto authRequestDto) {
        return this.authService.login(authRequestDto);
    }
}

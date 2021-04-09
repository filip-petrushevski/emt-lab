package com.emt.lab.api;

import com.emt.lab.dto.AuthRequestDto;
import com.emt.lab.model.User;
import com.emt.lab.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserContoller {

    private final UserService userService;

    public UserContoller(UserService userService) {
        this.userService = userService;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    public void register(@RequestBody AuthRequestDto authRequestDto) {
        this.userService.register(authRequestDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<User> getUsers() {
        return this.userService.findAllUsers();
    }
}

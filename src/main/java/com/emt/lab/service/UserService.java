package com.emt.lab.service;

import com.emt.lab.dto.AuthRequestDto;
import com.emt.lab.model.User;

import java.util.List;

public interface UserService {
    User findUserByUsername(String username);

    List<User> findAllUsers();

    void register(AuthRequestDto authRequestDto);
}

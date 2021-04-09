package com.emt.lab.service.impl;

import com.emt.lab.dto.AuthRequestDto;
import com.emt.lab.exception.BadRequestException;
import com.emt.lab.exception.NotFoundException;
import com.emt.lab.model.User;
import com.emt.lab.repository.RoleRepository;
import com.emt.lab.repository.UserRepository;
import com.emt.lab.service.UserService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerErrorException;

import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;


    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return findUserByUsername(username);
    }

    @Override
    public void register(AuthRequestDto requestDto) {
        if (userRepository.findByUsername(requestDto.getUsername()).isPresent()) {
            throw new BadRequestException("Register failed");
        }
        User user = new User();
        user.setUsername(requestDto.getUsername());
        user.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        user.setRoles(Set.of(roleRepository.findByName("ROLE_LIBRARIAN")
                        .orElseThrow(() -> new ServerErrorException("No LIBRARIAN role found", new Exception()))
                )
        );
        userRepository.save(user);
    }
}

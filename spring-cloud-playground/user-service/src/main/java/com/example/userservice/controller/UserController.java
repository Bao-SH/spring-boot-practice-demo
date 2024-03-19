package com.example.userservice.controller;


import com.example.userservice.dto.User;
import com.example.userservice.entity.UserEntity;
import com.example.userservice.mapper.UserMapper;
import com.example.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("/{id}")
    public User getUser(@PathVariable UUID id) {
        Optional<UserEntity> userEntity = userRepository.findById(id);
        return userEntity.map(UserMapper.INSTANCE::toUser).orElse(null);
    }
}

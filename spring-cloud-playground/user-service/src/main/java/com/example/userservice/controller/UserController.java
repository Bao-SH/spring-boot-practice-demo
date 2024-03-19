package com.example.userservice.controller;


import com.example.userservice.entity.UserEntity;
import com.example.userservice.mapper.UserMapper;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.request.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("/{id}")
    public UserEntity getUser(@PathVariable UUID id) {
        Optional<UserEntity> userEntity = userRepository.findById(id);
        return userEntity.orElse(null);
    }

    @PostMapping()
    public UUID createUser(@RequestBody UserRequest userRequest) {
        return userRepository.save(UserMapper.INSTANCE.toEntity(userRequest)).getId();
    }
}

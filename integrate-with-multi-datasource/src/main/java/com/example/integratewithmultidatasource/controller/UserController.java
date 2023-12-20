package com.example.integratewithmultidatasource.controller;

import com.example.integratewithmultidatasource.dto.UserDto;
import com.example.integratewithmultidatasource.mapper.UserMapper;
import com.example.integratewithmultidatasource.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @PostMapping("/user")
    public Integer create(@RequestBody UserDto userDto) {

        return userRepository.save(UserMapper.INSTANCE.toUserEntity(userDto)).getId();
    }
}

package com.example.multidatasourceconsumer.controller;

import com.example.multidatasourceconsumer.mapper.UserMapper;
import com.example.multidatasourceconsumer.repository.mysql.UserRepository;
import com.example.multidatasourceconsumer.dto.UserDto;
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

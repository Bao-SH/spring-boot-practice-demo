package com.example.integratewithswagger.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/oauth-test")
public class Oauth2Controller {

    @GetMapping("/")
    public String test(){
        return "succeed";
    }
}

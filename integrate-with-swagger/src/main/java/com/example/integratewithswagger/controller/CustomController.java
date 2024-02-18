package com.example.integratewithswagger.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/custom")
public class CustomController {

    @GetMapping("/test")
    public String test(){
        return "succeed";
    }
}

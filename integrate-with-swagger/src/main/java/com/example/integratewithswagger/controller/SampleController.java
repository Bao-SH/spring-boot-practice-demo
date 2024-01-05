package com.example.integratewithswagger.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/book")
public class SampleController {

    @GetMapping("/{id}")
    public String findById(@PathVariable long id) {
        return "succeed by " + id;
    }

    @GetMapping("/hello")
    public String greet() {
        return "hello";
    }
}


package com.example.integratewithoktaconsumer.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class HelloController {

    @GetMapping("/home")
    public String home() {
        return "home page";
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/secured")
    public String securedResource() {
        return "secured-resource";
    }

    @GetMapping("/secured-test")
    public String securedTest() {
        return "secured-test-resource";
    }
}
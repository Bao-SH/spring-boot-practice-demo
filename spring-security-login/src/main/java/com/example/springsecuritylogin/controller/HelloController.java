package com.example.springsecuritylogin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class HelloController {
    @GetMapping("/user")
    public String userGreet(Principal principal) {
        return "Hello user: " + principal.getName();
    }

    @GetMapping("/admin")
    public String adminGreet(Principal principal) {
        return "Hello admin: " + principal.getName();
    }
}

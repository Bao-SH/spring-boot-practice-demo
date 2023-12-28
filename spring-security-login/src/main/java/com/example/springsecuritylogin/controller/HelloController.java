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

    @GetMapping("/config")
    public String config(Principal principal) {
        return "Config page with user: " + principal.getName();
    }

    @GetMapping("/external")
    public String external(Principal principal) {
        return "External page with custom user: " + principal.getName();
    }
}

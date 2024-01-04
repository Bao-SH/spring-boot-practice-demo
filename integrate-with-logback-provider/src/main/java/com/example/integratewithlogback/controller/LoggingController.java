package com.example.integratewithlogback.controller;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class LoggingController {


    @GetMapping("/json")
    public String index() {
        Map<String, String> user = new HashMap<>();
        user.put("user_id", "87656");
        user.put("SSN", "786445563");
        user.put("address", "22 Street");
        user.put("city", "Chicago");
        user.put("Country", "U.S.");
        user.put("ip_address", "192.168.1.1");
        user.put("email_id", "spring@baeldung.com");
        user.put("msg", "test.com");
        JSONObject userDetails = new JSONObject(user);

        log.info("User JSON: {}", userDetails);
        return "Succeed";
    }

    @GetMapping("/info")
    public String info() {
        log.info("message is: try to mask this.");
        return "Succeed";
    }
}

package com.example.customannotationwithaop.controller;

import com.example.customannotationwithaop.annotation.Logging;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HelloController {

    @GetMapping("/hello")
    @Logging
    public String hello() {
        log.info("hello api is called.");
        return "hello world";
    }
}

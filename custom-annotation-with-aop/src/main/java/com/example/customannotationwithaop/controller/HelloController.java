package com.example.customannotationwithaop.controller;

import com.example.customannotationwithaop.annotation.Logging;
import com.example.customannotationwithaop.service.HiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class HelloController {

    private final HiService hiService;

    @GetMapping("/hello")
    @Logging
    public String hello() {
        log.info("hello api is called.");
        return "hello world";
    }

    @GetMapping("/hi")
    public String hi() {
        log.info("hi api is called.");
        return hiService.serve();
    }
}

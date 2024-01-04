package com.example.integratewithlogback.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class LoggingController {


    @GetMapping("/")
    public String index() {
        log.trace("A TRACE Message");
        log.debug("A DEBUG Message");
        log.info("A INFO Message");
        log.warn("A WARN Message");
        log.error("A ERROR Message");
        return "Succeed";
    }
}

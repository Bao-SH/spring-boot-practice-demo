package com.example.customannotationwithaop.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class HiService {
    public String serve() {
        log.info("hi serve is called.");
        return "hi world";
    }
}

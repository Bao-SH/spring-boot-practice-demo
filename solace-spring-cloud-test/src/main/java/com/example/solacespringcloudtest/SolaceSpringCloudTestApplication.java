package com.example.solacespringcloudtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;

import java.util.function.Consumer;

@SpringBootApplication
public class SolaceSpringCloudTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SolaceSpringCloudTestApplication.class, args);
    }

}

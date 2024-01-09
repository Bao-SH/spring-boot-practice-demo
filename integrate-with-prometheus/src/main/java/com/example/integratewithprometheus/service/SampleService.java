package com.example.integratewithprometheus.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class SampleService {

    public Number getNumber() {
        return new Random().nextInt(100);
    }
}

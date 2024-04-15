package com.example.dynamicproxydemo;

public class SmsServiceImpl implements SmsService{
    @Override
    public String send(String message) {
        System.out.println("Send message: " + message);
        return message;
    }
}

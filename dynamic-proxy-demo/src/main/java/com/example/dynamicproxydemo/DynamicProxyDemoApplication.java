package com.example.dynamicproxydemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.reflect.Proxy;

@SpringBootApplication
public class DynamicProxyDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DynamicProxyDemoApplication.class, args);
        SmsServiceImpl smsService = new SmsServiceImpl();
        SmsService proxyInstance = (SmsService) Proxy.newProxyInstance(smsService.getClass().getClassLoader(), smsService.getClass().getInterfaces(),
            new DynamicInvocationHandler(smsService));
        proxyInstance.send("java");
    }

}

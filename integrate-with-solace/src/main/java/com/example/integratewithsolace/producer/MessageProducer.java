package com.example.integratewithsolace.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class MessageProducer implements CommandLineRunner {

    private final JmsTemplate jmsTemplate;

    @Value("${solace.spring.queueName}")
    private String queueName;

    @Override
    public void run(String... args) {
        log.info("---------------Sending message-------------------");
        String msg = "Hello World";
        jmsTemplate.convertAndSend(queueName, msg);
        log.info("---------------Message sent----------------------");
    }
}

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

    @Value("${solace.spring.topicName}")
    private String topicName;

    @Override
    public void run(String... args) {
        log.info("---------------Sending message to queue-------------------");
        String msg = "Hello World to the queue";
        jmsTemplate.convertAndSend(queueName, msg);
        log.info("---------------Message sent----------------------");
        log.info("---------------Sending message to topic-------------------");
        String msg2 = "Hello World to the topic";
        jmsTemplate.setPubSubDomain(true);
        jmsTemplate.convertAndSend(topicName, msg2);
        log.info("---------------Message sent----------------------");
    }
}

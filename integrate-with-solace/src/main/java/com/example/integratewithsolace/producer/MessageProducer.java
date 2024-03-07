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

    @Value("${solace.spring.secondTopicName}")
    private String secondTopicName;

    @Override
    public void run(String... args) {
        infoSendingMessage(queueName);
        jmsTemplate.convertAndSend(queueName, getMsg(queueName));
        logMessageSent();

        jmsTemplate.setPubSubDomain(true);
        infoSendingMessage(topicName);
        jmsTemplate.convertAndSend(topicName, getMsg(topicName));
        logMessageSent();

        infoSendingMessage(secondTopicName);
        jmsTemplate.convertAndSend(secondTopicName, getMsg(secondTopicName));
        logMessageSent();
    }

    private static String getMsg(String destinationName) {
        return "Hello World to the " + destinationName;
    }

    private void infoSendingMessage(String destionationName) {
        log.info("---------------Sending message to " + destionationName + "-------------------");
    }

    private static void logMessageSent() {
        log.info("---------------Message sent----------------------");
    }
}

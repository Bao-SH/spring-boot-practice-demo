package com.example.solacemessageprovider;

import jakarta.jms.TextMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import static com.solacesystems.jms.SupportedProperty.JMSX_GROUP_ID;

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
//        sendToQueueName(queueName);
//
//        sendToPartitionedQueueName(queueName, "Group-0");
//        sendToPartitionedQueueName(queueName, "Group-1");
//        sendToPartitionedQueueName(queueName, "Group-2");

        sendToTopicName(topicName);

//        sendToTopicName(secondTopicName);

    }

    private void sendToTopicName(String topicName) {
        jmsTemplate.setPubSubDomain(true);
        infoSendingMessage(topicName);
        jmsTemplate.convertAndSend(topicName, getMsg(topicName));
        logMessageSent();
    }

    private void sendToPartitionedQueueName(String queueName, String partitionKey) {
        infoSendingMessage(queueName);
        jmsTemplate.send(queueName, session ->
        {
            TextMessage textMessage = session.createTextMessage("Hello world to the partitioned queue: " + partitionKey);
            textMessage.setStringProperty(JMSX_GROUP_ID, partitionKey);
            return textMessage;
        });
        logMessageSent();
    }

    private void sendToQueueName(String queueName) {
        infoSendingMessage(queueName);
        jmsTemplate.convertAndSend(queueName, getMsg(queueName));
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

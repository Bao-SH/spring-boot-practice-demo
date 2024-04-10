package com.example.solacespringcloudtest.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;

import java.util.function.Consumer;

@Configuration
@RequiredArgsConstructor
public class SolaceConfiguration {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${spring.kafka.topic}")
    private String topic;

    @Bean
    public Consumer<Message<String>> myFunction() {
        return message -> {
            System.out.println(message);
            sendMessage(message);
        };
    }

    private void sendMessage(Message<String> message) {
        kafkaTemplate.send(topic, message.getPayload());
        System.out.println("Send kafka topic successfully");
    }
}

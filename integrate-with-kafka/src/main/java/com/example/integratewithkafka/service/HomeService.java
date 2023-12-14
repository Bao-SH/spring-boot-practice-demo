package com.example.integratewithkafka.service;

import com.example.integratewithkafka.dto.Message;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
public class HomeService {
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${spring.kafka.topic}")
    private String topic;
    private final Gson gson = new GsonBuilder().create();

    public void produce() {
        var message = new Message();
        message.setId(System.currentTimeMillis());
        message.setMsg("Home Page");
        message.setSendTime(new Date());
        var sendResultCompletableFuture = kafkaTemplate.send(topic, gson.toJson(message));
        sendResultCompletableFuture.whenComplete((result, throwable) -> {
            if (throwable != null) {
                log.error("Message send failed, topic: %s".formatted(topic), throwable);
            } else {
                log.info(
                    "Produced kafka notification with topic: {}, messageId: {}.", topic, message.getId());
            }
        });
    }
}

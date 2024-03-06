package com.example.integratewithsolace.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MessageConsumer {

    @JmsListener(destination = "${solace.spring.queueName}")
    public void onReceive(Message<?> msg) {
        log.info("------------------------Receiving message-------------------");
        MessageHeaders headers = msg.getHeaders();
        log.info("Headers: " + headers);
        Object payload = msg.getPayload();
        log.info("Payload: " + payload);
        log.info("-----------------------Finished--------------------------");
    }
}

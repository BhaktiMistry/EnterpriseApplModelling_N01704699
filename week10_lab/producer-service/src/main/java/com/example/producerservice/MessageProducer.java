package com.example.producerservice;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendName(String name) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.NAME_QUEUE, name);
    }

    public void sendAge(String age) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.AGE_QUEUE, age);
    }
}
package com.example.consumerservice;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class MessageConsumer {

    @RabbitListener(queues = "nameQueue")
    public void receiveName(String name) {
        System.out.println("Received Name: " + name);
    }

    @RabbitListener(queues = "ageQueue")
    public void receiveAge(String age) {
        System.out.println("Received Age: " + age);
    }
}
package com.example.producerservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    @Autowired
    private MessageProducer producer;

    @GetMapping("/sendData")
    public String sendData() {
        producer.sendName("Bhakti");
        producer.sendAge("23");
        return "Messages sent: Bhakti and 23";
    }
}
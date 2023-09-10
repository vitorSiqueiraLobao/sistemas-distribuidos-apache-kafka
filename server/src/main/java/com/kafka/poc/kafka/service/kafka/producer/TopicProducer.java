package com.kafka.poc.kafka.service.kafka.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TopicProducer {

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    public void send(String topicName, String message){
        log.info("Mensagem enviada para o topico "+topicName);
        kafkaTemplate.send(topicName, message);
    }
}

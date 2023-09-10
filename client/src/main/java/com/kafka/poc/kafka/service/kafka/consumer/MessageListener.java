package com.kafka.poc.kafka.service.kafka.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.poc.kafka.model.Message;
import com.kafka.poc.kafka.model.MessageResponse;
import com.kafka.poc.kafka.service.kafka.producer.TopicProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Service
public class MessageListener {
    @Autowired
    TopicProducer topicProducer;
    private ObjectMapper mapper = new ObjectMapper();
    @KafkaListener(topics = "message-topic-response", groupId = "group_id_1",properties = {"enableAutoCommit=true"})
    public void listen(String message, Acknowledgment acknowledgment) {
        try{
            System.out.println("Mensagem recebida no topico message-topic-response: " + message);
            acknowledgment.acknowledge();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}

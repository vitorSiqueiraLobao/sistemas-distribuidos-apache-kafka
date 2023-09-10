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
    @KafkaListener(topics = "message-topic", groupId = "group_id_1",properties = {"enableAutoCommit=true"})
    public void listen(String message, Acknowledgment acknowledgment) {
        try{
            Message message1 = mapper.readValue(message, Message.class);
            String messageUppercase = message1.getMessage().toUpperCase();
            MessageResponse messageResponse = new MessageResponse(message1.getMessage(),messageUppercase);
            topicProducer.send("message-topic-response",mapper.writeValueAsString(messageResponse));
            acknowledgment.acknowledge();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}

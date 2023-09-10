package com.kafka.poc.kafka.service.kafka.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.poc.kafka.service.kafka.producer.TopicProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Service
public class EvalListener {
    @Autowired
    TopicProducer topicProducer;
    private ObjectMapper mapper = new ObjectMapper();


    @KafkaListener(topics = "evaluate-topic-response", groupId = "group_id_1",properties = {"enableAutoCommit=true"})
    public void listen(String message, Acknowledgment acknowledgment) {
        try{
            System.out.println("Mensagem recebida no topico evaluate-topic-response: " + message);
            acknowledgment.acknowledge();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}

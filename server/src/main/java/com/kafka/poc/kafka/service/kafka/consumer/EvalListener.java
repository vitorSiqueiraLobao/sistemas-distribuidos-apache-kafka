package com.kafka.poc.kafka.service.kafka.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.poc.kafka.model.Message;
import com.kafka.poc.kafka.model.MessageResponse;
import com.kafka.poc.kafka.service.EvaluateService;
import com.kafka.poc.kafka.service.kafka.producer.TopicProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Service
public class EvalListener {
    @Autowired
    private EvaluateService evaluateService;

    @Autowired
    TopicProducer topicProducer;
    private ObjectMapper mapper = new ObjectMapper();


    @KafkaListener(topics = "evaluate-topic", groupId = "group_id_1",properties = {"enableAutoCommit=true"})
    public void listen(String message, Acknowledgment acknowledgment) {
        try{
            Message message1 = mapper.readValue(message, Message.class);
            String expression = message1.getMessage().replaceAll("\\s+", "");//remove espaços em branco

            if (!expression.matches("[\\d+\\-*/().]+")) {
                throw new IllegalArgumentException("Invalid expression");
            }
            double evaluateResult = evaluateService.evaluate(expression);
            MessageResponse messageResponse = new MessageResponse(message1.getMessage(),String.valueOf(evaluateResult));
            topicProducer.send("evaluate-topic-response",mapper.writeValueAsString(messageResponse));
            acknowledgment.acknowledge();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}

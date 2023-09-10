package com.kafka.poc.kafka.controller;

import com.kafka.poc.kafka.service.kafka.producer.TopicProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EvaluateController {
    @Autowired
    TopicProducer topicProducer;


    @PostMapping("/enviar-calculo")
    public String enviarMensagem(@RequestBody String mensagem) {
        // Tópico Kafka para enviar a mensagem
        String topico = "evaluate-topic";

        // Enviar a mensagem para o tópico Kafka
        topicProducer.send(topico, mensagem);

        return "Mensagem enviada com sucesso para o tópico " + topico;
    }
}

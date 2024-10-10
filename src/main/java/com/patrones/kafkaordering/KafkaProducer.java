package com.patrones.kafkaordering;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.patrones.kafkaordering.entities.dto.OrderCreatedDTO;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOrderCreated(@JsonView OrderCreatedDTO orderCreated) {
        ObjectMapper mapper = new ObjectMapper();
        String jsonOrderCreated;

        try {
            jsonOrderCreated = mapper.writeValueAsString(orderCreated);
            kafkaTemplate.send("OrderCreated", jsonOrderCreated);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}

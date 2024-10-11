package com.patrones.kafkaordering;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.patrones.kafkaordering.entities.dto.OrderCreatedDTO;
import com.patrones.kafkaordering.entities.jpa.Venta;
import com.patrones.kafkaordering.entities.jpa.repositories.VentaRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class KafkaClient {
    private static final Logger LOGGER = Logger.getLogger(KafkaClient.class.getName());
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper mapper;
    private final VentaRepository ventaRepository;

    public KafkaClient(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper mapper, VentaRepository ventaRepository) {
        this.kafkaTemplate = kafkaTemplate;
        this.mapper = mapper;
        this.ventaRepository = ventaRepository;
    }

    public void sendOrderCreated(OrderCreatedDTO orderCreated) {
        String jsonOrderCreated;

        try {
            jsonOrderCreated = mapper.writeValueAsString(orderCreated);
            kafkaTemplate.send("OrderCreated", jsonOrderCreated);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @KafkaListener(topics = "OrderShipped")
    public void processOrderShipped(String content) {
        LOGGER.info("Evento Shipped recibido: " + content);
        updateOrder(getOrderIdFromJson(content), "SHIPPED");
    }

    @KafkaListener(topics = {"InventoryFailed","PaymentFailed"})
    public void processOrderFailed(String content) {
        LOGGER.info("Evento Failed recibido: " + content);
        updateOrder(getOrderIdFromJson(content), "FAILED");
    }

    private Integer getOrderIdFromJson(String content) {
        try {
            return mapper.readTree(content).get("orderId").asInt();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateOrder(Integer orderId, String newStatus) {
        Venta updatedVenta = ventaRepository.findById(orderId).get();
        updatedVenta.setEstado(newStatus);
        ventaRepository.save(updatedVenta);
    }
}

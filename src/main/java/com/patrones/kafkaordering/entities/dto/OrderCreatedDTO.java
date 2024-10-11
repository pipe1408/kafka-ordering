package com.patrones.kafkaordering.entities.dto;

import java.math.BigDecimal;
import java.util.List;

public record OrderCreatedDTO(
        Integer orderId,
        Integer customerId,
        List<SelectedProductDTO> productList,
        BigDecimal totalAmount) {
}

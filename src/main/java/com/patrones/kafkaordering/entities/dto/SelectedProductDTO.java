package com.patrones.kafkaordering.entities.dto;

public record SelectedProductDTO(
        Integer productId,
        Integer quantity) {
    public Integer getProductId() {
        return productId;
    }
}

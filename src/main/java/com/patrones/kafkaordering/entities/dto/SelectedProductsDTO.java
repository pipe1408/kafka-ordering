package com.patrones.kafkaordering.entities.dto;

public record SelectedProductsDTO(
        Integer productId,
        Integer quantity) {
    public Integer getProductId() {
        return productId;
    }
}

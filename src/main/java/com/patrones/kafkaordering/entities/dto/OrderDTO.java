package com.patrones.kafkaordering.entities.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record OrderDTO(
        @Email(message = "Invalid email format")
        @NotEmpty(message = "Email cannot be empty")
        String clientEmail,

        @NotNull(message = "Products list cannot be null")
        @NotEmpty(message = "Products list cannot be empty")
        List<SelectedProductsDTO> productsList,

        @NotNull(message = "Address cannot be null")
        @NotEmpty(message = "Address cannot be empty")
        String clientAddress,

        @NotNull(message = "Payment method cannot be null")
        @NotEmpty(message = "Payment method cannot be empty")
        String paymentMethod) {
}
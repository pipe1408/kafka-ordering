package com.patrones.kafkaordering.entities.dto;

import java.math.BigDecimal;

public record ProductoDTO(
        Integer id,
        String nombre,
        BigDecimal valor) { }

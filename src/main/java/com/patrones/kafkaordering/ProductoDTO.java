package com.patrones.kafkaordering;

import java.math.BigDecimal;

public record ProductoDTO(
        Integer id,
        String nombre,
        BigDecimal valor) { }

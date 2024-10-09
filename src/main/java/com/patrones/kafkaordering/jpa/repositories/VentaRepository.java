package com.patrones.kafkaordering.jpa.repositories;

import com.patrones.kafkaordering.jpa.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentaRepository extends JpaRepository<Venta, Integer> {

}

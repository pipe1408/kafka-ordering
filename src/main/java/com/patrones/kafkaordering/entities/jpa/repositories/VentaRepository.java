package com.patrones.kafkaordering.entities.jpa.repositories;

import com.patrones.kafkaordering.entities.jpa.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentaRepository extends JpaRepository<Venta, Integer> {

}

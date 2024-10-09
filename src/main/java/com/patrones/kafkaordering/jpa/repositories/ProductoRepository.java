package com.patrones.kafkaordering.jpa.repositories;

import com.patrones.kafkaordering.jpa.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {

}

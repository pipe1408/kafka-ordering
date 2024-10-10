package com.patrones.kafkaordering.entities.jpa.repositories;

import com.patrones.kafkaordering.entities.jpa.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    long countByIdIn(List<Integer> productos);
}

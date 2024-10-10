package com.patrones.kafkaordering.entities.jpa.repositories;

import com.patrones.kafkaordering.entities.jpa.VentaProducto;
import com.patrones.kafkaordering.entities.jpa.VentaProductoId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentaProductoRepository extends JpaRepository<VentaProducto, VentaProductoId> {

}

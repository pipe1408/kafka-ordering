package com.patrones.kafkaordering.jpa.repositories;

import com.patrones.kafkaordering.jpa.VentaProducto;
import com.patrones.kafkaordering.jpa.VentaProductoId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentaProductoRepository extends JpaRepository<VentaProducto, VentaProductoId> {

}

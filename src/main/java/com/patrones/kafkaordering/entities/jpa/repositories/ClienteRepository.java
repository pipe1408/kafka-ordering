package com.patrones.kafkaordering.entities.jpa.repositories;

import com.patrones.kafkaordering.entities.jpa.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    Cliente findByCorreo(String correo);
}

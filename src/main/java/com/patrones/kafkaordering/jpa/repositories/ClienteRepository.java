package com.patrones.kafkaordering.jpa.repositories;

import com.patrones.kafkaordering.jpa.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}

package com.patrones.kafkaordering;

import com.patrones.kafkaordering.entities.dto.ProductoDTO;
import com.patrones.kafkaordering.entities.jpa.Producto;
import com.patrones.kafkaordering.entities.jpa.repositories.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class OrderingService {

    private final ProductoRepository productoRepository;

    public OrderingService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<ProductoDTO> getAllProductos() {
        List<Producto> productos = productoRepository.findAll();
        return productos
                .stream()
                .map(producto -> new ProductoDTO(producto.getId(), producto.getNombre(), producto.getValor()))
                .collect(Collectors.toList());
    }
}

package com.patrones.kafkaordering;

import com.patrones.kafkaordering.jpa.repositories.ProductoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderingController {
    private final OrderingService orderingService;
    ProductoRepository productoRepository;

    public OrderingController(ProductoRepository productoRepository, OrderingService orderingService) {
        this.productoRepository = productoRepository;
        this.orderingService = orderingService;
    }

    @GetMapping("/productos")
    public ResponseEntity<List<ProductoDTO>> getAllProductos() {
        return new ResponseEntity<>(orderingService.getAllProductos(), HttpStatus.OK);
    }
}

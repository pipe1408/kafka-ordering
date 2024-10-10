package com.patrones.kafkaordering;

import com.patrones.kafkaordering.entities.dto.OrderDTO;
import com.patrones.kafkaordering.entities.dto.ProductoDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class OrderingController {
    private final OrderingService orderingService;

    public OrderingController(OrderingService orderingService) {
        this.orderingService = orderingService;
    }

    @GetMapping("/productos")
    public ResponseEntity<List<ProductoDTO>> getAllProductos() {
        return ResponseEntity.ok(orderingService.getAllProductos());
    }

    @PostMapping("/ordenes")
    public ResponseEntity<OrderDTO> postOrder(@Valid @RequestBody OrderDTO orderDTO) {
        return ResponseEntity.status(orderingService.placeOrder(orderDTO)).body(orderDTO);
    }
}

package com.patrones.kafkaordering;

import com.patrones.kafkaordering.entities.dto.OrderDTO;
import com.patrones.kafkaordering.entities.dto.ProductoDTO;
import com.patrones.kafkaordering.entities.dto.SelectedProductsDTO;
import com.patrones.kafkaordering.entities.jpa.Producto;
import com.patrones.kafkaordering.entities.jpa.repositories.ClienteRepository;
import com.patrones.kafkaordering.entities.jpa.repositories.ProductoRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class OrderingService {

    private final ProductoRepository productoRepository;
    private final ClienteRepository clienteRepository;

    public OrderingService(ProductoRepository productoRepository, ClienteRepository clienteRepository) {
        this.productoRepository = productoRepository;
        this.clienteRepository = clienteRepository;
    }

    public List<ProductoDTO> getAllProductos() {
        List<Producto> productos = productoRepository.findAll();
        return productos
                .stream()
                .map(producto -> new ProductoDTO(producto.getId(), producto.getNombre(), producto.getValor()))
                .collect(Collectors.toList());
    }

    public boolean emailExists(String email) {
        return (clienteRepository.findByCorreo(email) != null);
    }

    public boolean allProductosExist(List<SelectedProductsDTO> productos) {
        if (productos.isEmpty()) {
            return false;
        }
        List<Integer> listaProductos = productos
                .stream()
                .map(SelectedProductsDTO::getProductId)
                .toList();
        long existingCount = productoRepository.countByIdIn(listaProductos);
        return existingCount == productos.size();
    }

    public HttpStatus placeOrder(@Valid OrderDTO orderDTO) {
        if (!emailExists(orderDTO.clientEmail())) {
            return HttpStatus.UNAUTHORIZED;
        }
        if (!allProductosExist(orderDTO.productsList())) {
            return HttpStatus.NOT_FOUND;
        }
        return HttpStatus.CREATED;
    }
}

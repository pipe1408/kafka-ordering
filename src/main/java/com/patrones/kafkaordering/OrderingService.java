package com.patrones.kafkaordering;

import com.patrones.kafkaordering.entities.dto.OrderDTO;
import com.patrones.kafkaordering.entities.dto.ProductoDTO;
import com.patrones.kafkaordering.entities.dto.SelectedProductsDTO;
import com.patrones.kafkaordering.entities.jpa.*;
import com.patrones.kafkaordering.entities.jpa.repositories.ClienteRepository;
import com.patrones.kafkaordering.entities.jpa.repositories.ProductoRepository;
import com.patrones.kafkaordering.entities.jpa.repositories.VentaProductoRepository;
import com.patrones.kafkaordering.entities.jpa.repositories.VentaRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class OrderingService {

    private final ProductoRepository productoRepository;
    private final ClienteRepository clienteRepository;
    private final VentaRepository ventaRepository;
    private final VentaProductoRepository ventaProductoRepository;
    private Venta venta;

    public OrderingService(ProductoRepository productoRepository, ClienteRepository clienteRepository, VentaRepository ventaRepository, VentaProductoRepository ventaProductoRepository) {
        this.productoRepository = productoRepository;
        this.clienteRepository = clienteRepository;
        this.ventaRepository = ventaRepository;
        this.ventaProductoRepository = ventaProductoRepository;
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
        List<Integer> listaProductos = productos
                .stream()
                .map(SelectedProductsDTO::getProductId)
                .toList();
        long existingCount = productoRepository.countByIdIn(listaProductos);
        return existingCount == productos.size();
    }

    public HttpStatus placeOrder(OrderDTO orderDTO) {
        if (!emailExists(orderDTO.clientEmail())) {
            return HttpStatus.UNAUTHORIZED;
        }
        if (!allProductosExist(orderDTO.productsList())) {
            return HttpStatus.NOT_FOUND;
        }

        venta = saveVenta(orderDTO);
        saveVentaProductos(venta ,orderDTO.productsList());
        return HttpStatus.CREATED;
    }

    private Venta saveVenta(@Valid OrderDTO orderDTO) {
        Cliente cliente = clienteRepository.findByCorreo(orderDTO.clientEmail());
        venta = new Venta();

        venta.setEstado("CREATED");
        venta.setIdCliente(cliente);
        venta.setDireccion(orderDTO.clientAddress());
        venta.setMetodoPago(orderDTO.paymentMethod());
        venta.setFecha(Instant.now());

        return ventaRepository.save(venta);
    }

    private void saveVentaProductos(Venta currentVenta, List<SelectedProductsDTO> selectedProducts) {
        for (SelectedProductsDTO selectedProduct : selectedProducts) {
            VentaProducto ventaProducto = new VentaProducto();
            VentaProductoId ventaProductoId = new VentaProductoId();

            ventaProductoId.setIdCompra(currentVenta.getId());
            ventaProductoId.setIdProducto(selectedProduct.productId());
            ventaProducto.setId(ventaProductoId);

            ventaProducto.setIdCompra(currentVenta);
            ventaProducto.setIdProducto(productoRepository.findById(selectedProduct.productId()).get());
            ventaProducto.setCantidad(selectedProduct.quantity());

            ventaProductoRepository.save(ventaProducto);
        }
    }
}

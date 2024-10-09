package com.patrones.kafkaordering.jpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "venta_producto", schema = "parcial2")
public class VentaProducto {
    @EmbeddedId
    private VentaProductoId id;

    @MapsId("idCompra")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_compra", nullable = false)
    private Venta idCompra;

    @MapsId("idProducto")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto idProducto;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

}
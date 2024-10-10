package com.patrones.kafkaordering.jpa;

import jakarta.persistence.*;

@Entity
@Table(name = "venta_producto")
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

    public VentaProductoId getId() {
        return id;
    }

    public void setId(VentaProductoId id) {
        this.id = id;
    }

    public Venta getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(Venta idCompra) {
        this.idCompra = idCompra;
    }

    public Producto getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Producto idProducto) {
        this.idProducto = idProducto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

}
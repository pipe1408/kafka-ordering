package com.patrones.kafkaordering.entities.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import org.hibernate.Hibernate;

import java.io.Serial;
import java.util.Objects;

@Embeddable
public class VentaProductoId implements java.io.Serializable {
    @Serial
    private static final long serialVersionUID = -5970230490908096249L;
    @NotNull
    @Column(name = "id_compra", nullable = false)
    private Integer idCompra;

    @NotNull
    @Column(name = "id_producto", nullable = false)
    private Integer idProducto;

    public Integer getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(Integer idCompra) {
        this.idCompra = idCompra;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        VentaProductoId entity = (VentaProductoId) o;
        return Objects.equals(this.idCompra, entity.idCompra) &&
                Objects.equals(this.idProducto, entity.idProducto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCompra, idProducto);
    }

}
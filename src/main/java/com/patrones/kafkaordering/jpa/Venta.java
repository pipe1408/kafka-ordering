package com.patrones.kafkaordering.jpa;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "venta")
public class Venta {
    @Id
    @Column(name = "id_compra", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente idCliente;

    @Column(name = "estado", length = 50)
    private String estado;

    @Column(name = "direccion", length = 100)
    private String direccion;

    @Column(name = "metodo_pago", length = 50)
    private String metodoPago;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

}
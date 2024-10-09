package com.patrones.kafkaordering.jpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Entity
@Table(name = "venta", schema = "parcial2")
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

}
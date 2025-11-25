package com.pasteleria.micro_carrito.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productoId; // viene del micro Core (producto)
    private Integer cantidad;
    private Double precioUnitario;

    @ManyToOne
    @JoinColumn(name = "carrito_id")
    private Carrito carrito;

    public Double getSubtotal() {
        return cantidad * precioUnitario;
    }
}

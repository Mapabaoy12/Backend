package com.pasteleria.micro_carrito.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "detalle_boletas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetalleBoleta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long productoId;
    private Integer cantidad;
    private Double precioUnitario;
    private Double subtotal;
    
    @ManyToOne
    @JoinColumn(name = "boleta_id")
    private Boleta boleta;
}

package com.pasteleria.micro_carrito.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemDTO {

    private Long productoId;
    private Integer cantidad;
    private Double precioUnitario;
    private Double subtotal;

}

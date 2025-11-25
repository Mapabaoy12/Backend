package com.pasteleria.micro_carrito.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarritoDTO {

    private Long id;
    private Long usuarioId;
    private List<ItemDTO> items;
    private Double total;
    
}

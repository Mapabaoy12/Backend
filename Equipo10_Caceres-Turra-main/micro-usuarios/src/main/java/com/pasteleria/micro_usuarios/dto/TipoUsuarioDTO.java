package com.pasteleria.micro_usuarios.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TipoUsuarioDTO {
    private Long id;
    private String nombre;
    private String descripcion;
}

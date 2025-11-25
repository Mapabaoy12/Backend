package com.pasteleria.micro_usuarios.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioDto {
    private long id;
    private String nombre;
    private String apellido;
    private String email;
    private String tipoUsuario;
}

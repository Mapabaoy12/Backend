package com.pasteleria.micro_usuarios.service;

import java.util.List;

import com.pasteleria.micro_usuarios.dto.UsuarioDto;

public interface UsuarioService {
    
    List<UsuarioDto> listarUsuarios();
    UsuarioDto obtenerPorID(long id);
    UsuarioDto crearUsuario(UsuarioDto usuarioDto);
    UsuarioDto actualizarUsuario(long id, UsuarioDto usuarioDto);
    void eliminarUsuario(Long id);
}

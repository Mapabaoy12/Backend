package com.pasteleria.micro_usuarios.service;

import java.util.List;

import com.pasteleria.micro_usuarios.dto.TipoUsuarioDTO;

public interface TipoUsuarioService {
    
    List<TipoUsuarioDTO> listarTipos();
    TipoUsuarioDTO buscarPorId(Long id);
    TipoUsuarioDTO crear(TipoUsuarioDTO tipoUsuarioDTO);
    TipoUsuarioDTO actualizar(Long id, TipoUsuarioDTO tipoUsuarioDTO);
    void eliminar(Long id);
}

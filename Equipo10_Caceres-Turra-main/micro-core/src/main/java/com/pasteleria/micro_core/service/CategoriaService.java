package com.pasteleria.micro_core.service;

import java.util.List;

import com.pasteleria.micro_core.dto.CategoriaDTO;

public interface CategoriaService {

    List<CategoriaDTO> listarCategorias();
    CategoriaDTO obtenerCategoriaPorId(Long id);
    CategoriaDTO guardarCategoria(CategoriaDTO categoriaDTO);
    CategoriaDTO actualizarCategoria(Long id, CategoriaDTO categoriaDTO);
    void eliminarCategoria(Long id);

}

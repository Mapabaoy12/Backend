package com.pasteleria.micro_core.service;

import java.util.List;

import com.pasteleria.micro_core.dto.ProductoDTO;

public interface ProductoService {

    List<ProductoDTO> listarProductos();
    ProductoDTO obtenerPorId(Long id);
    ProductoDTO guardarProducto(ProductoDTO productoDTO);
    ProductoDTO actualizarProducto(Long id, ProductoDTO productoDTO);
    void eliminarProducto(Long id);
    List<ProductoDTO> obtenerProductosPorCategoria(String nombreCate);
}

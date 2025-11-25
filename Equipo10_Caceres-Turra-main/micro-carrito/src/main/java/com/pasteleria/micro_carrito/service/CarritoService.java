package com.pasteleria.micro_carrito.service;

import com.pasteleria.micro_carrito.dto.CarritoDTO;
import com.pasteleria.micro_carrito.dto.ItemDTO;

public interface CarritoService {

    CarritoDTO crearCarrito(Long usuarioId);
    CarritoDTO agregarItem(Long carritoId, ItemDTO itemDTO);
    CarritoDTO obtenerCarrito(Long carritoId);
    void eliminarItem(Long carritoId, Long itemId);

}

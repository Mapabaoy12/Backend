package com.pasteleria.micro_carrito.service;

import com.pasteleria.micro_carrito.entity.Boleta;

public interface BoletaService {
    Boleta generarBoleta(Long carritoId);
} 
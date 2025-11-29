package com.pasteleria.micro_carrito.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pasteleria.micro_carrito.entity.Boleta;
import com.pasteleria.micro_carrito.service.BoletaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/boletas")
@RequiredArgsConstructor
public class BoletaController {
    private final BoletaService boletaService;

    @PostMapping("/generar/{carritoId}")
    public ResponseEntity<Boleta> generarBoleta(@PathVariable Long carritoId) {
        Boleta nuevaBoleta = boletaService.generarBoleta(carritoId);
        return ResponseEntity.ok(nuevaBoleta);
    }
    
}

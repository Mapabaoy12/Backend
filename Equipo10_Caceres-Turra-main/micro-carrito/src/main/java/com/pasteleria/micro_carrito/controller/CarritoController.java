package com.pasteleria.micro_carrito.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pasteleria.micro_carrito.dto.CarritoDTO;
import com.pasteleria.micro_carrito.dto.ItemDTO;
import com.pasteleria.micro_carrito.service.CarritoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/carrito")
@RequiredArgsConstructor
public class CarritoController {

    private final CarritoService carritoService;

    @PostMapping("/crear/{usuarioId}")
    public ResponseEntity<CarritoDTO> crearCarrito(@PathVariable Long usuarioId) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(carritoService.crearCarrito(usuarioId));
    }

    @PostMapping("agregar-item/{carritoId}/")
    public ResponseEntity<CarritoDTO> agregarItem(@PathVariable Long carritoId, @RequestBody ItemDTO itemDTO) {
        return ResponseEntity.ok(carritoService.agregarItem(carritoId, itemDTO));
    }

    @GetMapping("/{carritoId}")
    public ResponseEntity<CarritoDTO> obtenerCarrito(@PathVariable Long carritoId) {
        return ResponseEntity.ok(carritoService.obtenerCarrito(carritoId));
    }

    @DeleteMapping("/{carritoId}/eliminar-item/{itemId}")
    public ResponseEntity<Void> eliminarItem(@PathVariable Long carritoId, @PathVariable Long itemId) {
        carritoService.eliminarItem(carritoId, itemId);
        return ResponseEntity.noContent().build();
    }

}

package com.pasteleria.micro_core.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pasteleria.micro_core.dto.CategoriaDTO;
import com.pasteleria.micro_core.service.CategoriaService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/categorias")
@RequiredArgsConstructor

public class CategoriaController {

    private final CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> listar() {
        return ResponseEntity.ok(categoriaService.listarCategorias());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(categoriaService.obtenerCategoriaPorId(id));
    }

    @PostMapping
    public ResponseEntity<CategoriaDTO> crear(@RequestBody CategoriaDTO dto) {
        return ResponseEntity.ok(categoriaService.guardarCategoria(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO> actualizar(@PathVariable Long id, @RequestBody CategoriaDTO dto) {
        return ResponseEntity.ok(categoriaService.actualizarCategoria(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        categoriaService.eliminarCategoria(id);
        return ResponseEntity.noContent().build();
    }
}

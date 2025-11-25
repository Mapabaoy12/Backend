package com.pasteleria.micro_usuarios.controller;

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

import com.pasteleria.micro_usuarios.dto.TipoUsuarioDTO;
import com.pasteleria.micro_usuarios.service.TipoUsuarioService;

@RestController
@RequestMapping("/api/tipos-usuario")
public class TipoUsuarioController {

    private final TipoUsuarioService tipoUsuarioService;

    public TipoUsuarioController(TipoUsuarioService tipoUsuarioService) {
        this.tipoUsuarioService = tipoUsuarioService;
    }

    @GetMapping
    public ResponseEntity<List<TipoUsuarioDTO>> listar() {
        return ResponseEntity.ok(tipoUsuarioService.listarTipos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoUsuarioDTO> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(tipoUsuarioService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<TipoUsuarioDTO> crear(@RequestBody TipoUsuarioDTO tipo) {
        return ResponseEntity.ok(tipoUsuarioService.crear(tipo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoUsuarioDTO> actualizar(@PathVariable Long id, @RequestBody TipoUsuarioDTO tipo) {
        return ResponseEntity.ok(tipoUsuarioService.actualizar(id, tipo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        tipoUsuarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}

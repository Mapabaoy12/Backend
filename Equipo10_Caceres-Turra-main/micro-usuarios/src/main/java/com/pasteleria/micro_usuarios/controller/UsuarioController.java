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

import com.pasteleria.micro_usuarios.dto.UsuarioDto;
import com.pasteleria.micro_usuarios.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioServ;

    @GetMapping
    public ResponseEntity<List<UsuarioDto>> listarUsuarios(){
        return ResponseEntity.ok(usuarioServ.listarUsuarios());
        
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDto> usuarioPorId(@PathVariable Long id){
        return ResponseEntity.ok(usuarioServ.obtenerPorID(id));
    }

    @PostMapping
    public ResponseEntity<UsuarioDto> crearUsuario(@RequestBody UsuarioDto usuario){
        return ResponseEntity.ok(usuarioServ.crearUsuario(usuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDto> actualizarUsuario(@PathVariable Long id, @RequestBody UsuarioDto usuario){
        return ResponseEntity.ok(usuarioServ.actualizarUsuario(id, usuario));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id){
        usuarioServ.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.pasteleria.micro_usuarios.dto.UsuarioDto;
import com.pasteleria.micro_usuarios.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioServ;

    @GetMapping("/saludo")
    public ResponseEntity<Object> obtenerSaludoBienvenida(@RequestParam(required = false) String nombre) {
    String azureFunctionUrl = "https://funcion-pasteles-cqgrafawbuayfxeh.westus3-01.azurewebsites.net/api/SaludoBienvenida"; 
    
    if (nombre != null && !nombre.isEmpty()) {
        azureFunctionUrl += "?nombre=" + nombre;
    }

    RestTemplate restTemplate = new RestTemplate();
    try {
        String respuesta = restTemplate.getForObject(azureFunctionUrl, String.class);
        
        return ResponseEntity.ok(respuesta);
    } catch (Exception e) {
        return ResponseEntity.internalServerError().body("Error al contactar con Azure Function: " + e.getMessage());
    }
}

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

package com.pasteleria.micro_usuarios.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pasteleria.micro_usuarios.dto.AuthRequestDto;
import com.pasteleria.micro_usuarios.service.JwtService;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.Authentication;




@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public String getToken(@RequestBody AuthRequestDto authRequest){
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
    if (authenticate.isAuthenticated()){
        return jwtService.generateToken(authRequest.getEmail());
    } else {
        throw new RuntimeException("Acceso invalido");
    }
    }
    
}

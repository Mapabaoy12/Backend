package com.pasteleria.micro_usuarios.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pasteleria.micro_usuarios.entity.UsuarioEntity;
import com.pasteleria.micro_usuarios.repository.UsuarioRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    
    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UsuarioEntity> usuario = repository.findByEmail(email);
        return usuario.map(CustomUserDetails::new)
            .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con email " + email));
    }

}

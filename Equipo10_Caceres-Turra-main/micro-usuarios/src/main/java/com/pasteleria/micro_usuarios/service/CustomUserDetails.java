package com.pasteleria.micro_usuarios.service;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.pasteleria.micro_usuarios.entity.UsuarioEntity;

public class CustomUserDetails implements UserDetails {
    private final UsuarioEntity usuario;

    public CustomUserDetails(UsuarioEntity usuario) {
        this.usuario = usuario;

    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        String nombreRol = usuario.getTipoUsuario().getNombre();
        return Collections.singletonList(new SimpleGrantedAuthority(nombreRol));
    }

    @Override
    public String getPassword(){
        return usuario.getContrasenia();
    }

    @Override
    public String getUsername(){
        return usuario.getEmail();
    }

    @Override
    public boolean isAccountNonExpired(){
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isEnabled(){
        return true;
    }
    
}

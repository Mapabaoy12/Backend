package com.pasteleria.micro_usuarios.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    public static final String SECRET_STRING = "LamejorUMAMUSUMEESGOLDSHIPHELLYEACOREYEYEYEYEYEYE";

    public String generateToken(String userName, String role) { // <--- Agrega parámetro role
    Map<String, Object> claims = new HashMap<>();
    claims.put("role", role); // <--- Agrega el rol a los claims
    return createToken(claims, userName);
}

    private String createToken(Map<String, Object> claims, String userName){
        return Jwts.builder()
            .setClaims(claims)
            .setSubject(userName)
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis()))
            .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
    }

    private Key getSignKey(){
        byte[] keyBytes = io.jsonwebtoken.io.Decoders.BASE64.decode(SECRET_STRING);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    
}

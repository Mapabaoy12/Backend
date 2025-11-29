package com.pasteleria.micro_carrito.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pasteleria.micro_carrito.entity.Boleta;

@Repository
public interface BoletaRepository extends JpaRepository<Boleta, Long> {
    
}
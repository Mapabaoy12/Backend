package com.pasteleria.micro_carrito.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pasteleria.micro_carrito.entity.Carrito;

@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Long>{

}

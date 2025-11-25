package com.pasteleria.micro_carrito.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pasteleria.micro_carrito.entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>{

}

package com.pasteleria.micro_usuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pasteleria.micro_usuarios.entity.TipoUsuarioEntity;

@Repository
public interface TipoUsuarioRepository extends JpaRepository<TipoUsuarioEntity, Long>{

    TipoUsuarioEntity findByNombre(String nombre);

}

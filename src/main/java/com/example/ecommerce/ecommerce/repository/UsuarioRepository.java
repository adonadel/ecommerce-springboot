package com.example.ecommerce.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.ecommerce.ecommerce.model.UsuarioEntity;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long>{

    UserDetails findByEmail(String email);
    List<UsuarioEntity> findByNomeContaining(String termo);
}

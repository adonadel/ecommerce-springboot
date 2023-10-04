package com.example.ecommerce.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.ecommerce.ecommerce.model.UsuarioEntity;
import com.example.ecommerce.ecommerce.repository.UsuarioRepository;

public class UsuarioController {
    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping(value="/")
    public void get() 
    {

    }

    @PostMapping (value="/usuario")
    public ResponseEntity<UsuarioEntity> salvar(@RequestBody UsuarioEntity usuario)
    {
        UsuarioEntity _user = usuarioRepository.save(usuario);
        return new ResponseEntity<>(_user, HttpStatus.CREATED);
    }
}

package com.example.ecommerce.ecommerce.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.ecommerce.model.UsuarioEntity;
import com.example.ecommerce.ecommerce.repository.UsuarioRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UsuarioController {
    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping(value="/usuario/listar")
    public List<UsuarioEntity> getMethodName() 
    {
        return usuarioRepository.findAll();
    }

    @GetMapping(path={"/usuario/{id}"})
    public UsuarioEntity getUser(@PathVariable long id) 
    {
        return usuarioRepository.findById(id).get();
    }

    @PostMapping (value="/usuario")
    public ResponseEntity<UsuarioEntity> salvar(@RequestBody UsuarioEntity usuario)
    {
        usuario.createdAt = LocalDateTime.now();
        usuario.updatedAt = LocalDateTime.now();
        UsuarioEntity user = usuarioRepository.save(usuario);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @DeleteMapping (path = {"/usuario/{id}"})
    public ResponseEntity <?> excluir(@PathVariable long id)
    {
        usuarioRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    // @PutMapping (path = {"/usuario/{id}"})
    // public ResponseEntity<UsuarioEntity> editar(@PathVariable long id, @RequestBody UsuarioEntity updated)
    // {
    //     Optional<UsuarioEntity> usuario = usuarioRepository.findById(id);
    //     usuario.set
    //     UsuarioEntity user = usuarioRepository.save(updated);
    //     return new ResponseEntity<>(user, HttpStatus.OK);
    // }
}

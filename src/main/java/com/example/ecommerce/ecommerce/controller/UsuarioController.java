package com.example.ecommerce.ecommerce.controller;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.ecommerce.model.RegisterDTO;
import com.example.ecommerce.ecommerce.model.UsuarioEntity;
import com.example.ecommerce.ecommerce.repository.UsuarioRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UsuarioController {
    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping(value="/usuarios/listar")
    public List<UsuarioEntity> getMethodName() 
    {
        return usuarioRepository.findAll();
    }

    @GetMapping(path={"/usuarios/{id}"})
    public UsuarioEntity getUser(@PathVariable long id) 
    {
        return usuarioRepository.findById(id).get();
    }

    @PostMapping (value="/usuarios")
    public ResponseEntity<UsuarioEntity> salvar(@RequestBody RegisterDTO usuario)
    {
        if (this.usuarioRepository.findByEmail(usuario.email()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(usuario.password());
        UsuarioEntity new_user = new UsuarioEntity(usuario.nome(), usuario.email(),encryptedPassword,usuario.role());

        new_user.createdAt = LocalDateTime.now();
        new_user.updatedAt = LocalDateTime.now();
        this.usuarioRepository.save(new_user);
        return new ResponseEntity<>(new_user, HttpStatus.CREATED);
        // UsuarioEntity user = usuarioRepository.save(usuario);
        // return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @DeleteMapping (path = {"/usuarios/{id}"})
    public ResponseEntity <?> excluir(@PathVariable long id)
    {
        usuarioRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping (value = "/usuarios/{id}")
    public ResponseEntity<UsuarioEntity> editar(@PathVariable long id, @RequestBody UsuarioEntity updated)
    {
        UsuarioEntity usuario = usuarioRepository.findById(id).get();
        
        usuario.setNome(updated.nome);
        usuario.setEmail(updated.email);
        usuario.setPassword(updated.password);
        
        usuarioRepository.save(usuario);
        return ResponseEntity.ok().build();
    }

    @GetMapping (value = "/usuarios/pesquisar/{termo}")
    public ResponseEntity<List<UsuarioEntity>> pesquisar(@PathVariable String termo)
    {
        List<UsuarioEntity> usuarios = this.usuarioRepository.findByNomeContaining(termo);

        return ResponseEntity.ok(usuarios);
    }
}

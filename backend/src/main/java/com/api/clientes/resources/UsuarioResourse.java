package com.api.clientes.resources;

import com.api.clientes.Service.UsuarioService;
import com.api.clientes.model.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;


@RestController
@RequestMapping("/usuarios")
//@CrossOrigin("http://localhost:4200")
public class UsuarioResourse {

    @Autowired
    private UsuarioService service;

    @PostMapping
    public ResponseEntity<Usuario> insert(@Valid @RequestBody Usuario usuario){
        usuario = service.insert(usuario);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(usuario);
    }
}
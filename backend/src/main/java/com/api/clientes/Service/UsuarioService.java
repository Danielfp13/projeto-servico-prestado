package com.api.clientes.Service;

import com.api.clientes.Service.exception.ObjectNotFoundException;
import com.api.clientes.Service.exception.UsuarioCadastradoException;
import com.api.clientes.model.entity.Usuario;
import com.api.clientes.repository.UsuarioRepositoty;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private UsuarioRepositoty repositoty;

    public Usuario insert(Usuario usuario) {
        if(findByUsername(usuario.getUsername())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Já existe usuário com o login " + usuario.getUsername() + "cadastrado");
        }else {
            usuario.setId(null);
            usuario.setPassword(encoder.encode(usuario.getPassword()));

            return repositoty.save(usuario);
        }
    }

    public boolean findByUsername(String username){
        return repositoty.existsByUsername(username);
    }
}

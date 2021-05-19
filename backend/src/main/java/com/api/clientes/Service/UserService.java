package com.api.clientes.Service;

import com.api.clientes.model.entity.Usuario;
import com.api.clientes.repository.UsuarioRepositoty;
import com.api.clientes.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UsuarioRepositoty repositoty;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = repositoty.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Login não encontrado."));

        System.out.println("DENTRO userdetails" + usuario.getPassword());
        return new UserSS(usuario.getId(), usuario.getUsername(), usuario.getPassword());

    }
}
package com.api.clientes.repository;

import com.api.clientes.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepositoty extends JpaRepository<Usuario,Integer> {

    Optional<Usuario> findByUsername(String username);

    boolean existsByUsername(String username);
}

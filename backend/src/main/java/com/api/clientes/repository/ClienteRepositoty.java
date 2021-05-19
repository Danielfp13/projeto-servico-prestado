package com.api.clientes.repository;

import com.api.clientes.model.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepositoty extends JpaRepository<Cliente,Integer> {

}

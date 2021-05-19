package com.api.clientes.repository;

import com.api.clientes.model.entity.ServicoPrestado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicoPrestadoRepositoty extends JpaRepository<ServicoPrestado,Integer> {

   // @Query("select s from ServicoPrestado s join s.cliente c" +
     //       " where upper( c.nome ) like upper( :nome ) and MONTH(s.data) =:mes   ")
    @Query(value = "SELECT * FROM SERVICO_PRESTADO S\n" +
            "INNER JOIN CLIENTE C ON C.ID = S.ID_CLIENTE \n" +
            "WHERE UPPER(C.NOME  ) LIKE UPPER(:nome)  AND MONTH(S.DATA) = :mes  ",nativeQuery = true)
    List<ServicoPrestado> findByNameClienteAndMonth(
            @Param("nome") String nome, @Param("mes") Integer mes);
}

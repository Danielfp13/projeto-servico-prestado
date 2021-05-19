package com.api.clientes.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
   // @NotEmpty(message = "{campo.nome.obrigatorio}")
    @NotEmpty(message = "O campo nome é obrigatório.!!")
    @Column(nullable = false, length = 150)
    private String nome;

    @Column(nullable = false, length = 11)
    //@NotEmpty(message = "{campo.cpf.obrigatorio}")
  //  @CPF(message = "{campo.cpf.invalido}")
    @NotEmpty(message = "O campo cpf é obrigatório.!!")
    @CPF(message = "O campo cpf está incorreto.!!")
    private String cpf;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column(name = "data_cadastro", updatable = false)
    private LocalDate dataCadastro;

    @PrePersist
    public void  prePersite(){
        setDataCadastro(LocalDate.now());
    }
}

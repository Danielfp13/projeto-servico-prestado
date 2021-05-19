package com.api.clientes.dto;

import com.api.clientes.model.entity.Cliente;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ServicoPrestadoDto {

    @NotEmpty(message = "O campo descrição é obrigatorio!")
    private String descricao;

    @NotEmpty(message = "O campo preço é obrigatorio!")
    private String preco;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @NotEmpty(message = "O campo data é obrigatorio!")
    private String data;

    @NotNull(message = "O campo cliente é obrigatorio!")
    private Integer idCliente;


}

package com.api.clientes.resources.exception;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public class ApiErros {

    List<String> erros = new ArrayList<>();

    public ApiErros( String mensagem){
        this.erros = Arrays.asList(mensagem);
    }

    public ApiErros(List<String> erros) {
        this.erros = erros;
    }
}

package com.api.clientes.resources;

import com.api.clientes.Service.exception.UsuarioCadastradoException;
import com.api.clientes.resources.exception.ApiErros;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.*;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class AppliacationControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ApiErros hadlerValidationErrors(MethodArgumentNotValidException e){
        BindingResult bindingResult = e.getBindingResult();
       List<String> messages =  bindingResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage()).collect(Collectors.toList());
        return new ApiErros(messages);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity hadlerValidationErrors(ResponseStatusException e){
        String menssagemError = e.getReason();
        HttpStatus codStatus= e.getStatus();
        ApiErros apiErros = new ApiErros(menssagemError);
      return new ResponseEntity(apiErros,codStatus);
    }

}

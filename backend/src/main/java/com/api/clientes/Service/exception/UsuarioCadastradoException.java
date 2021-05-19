package com.api.clientes.Service.exception;

public class UsuarioCadastradoException extends RuntimeException{
    public UsuarioCadastradoException() {
        super();
    }

    public UsuarioCadastradoException(String message) {
        super(message);
    }
}

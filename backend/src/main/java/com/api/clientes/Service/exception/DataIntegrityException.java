package com.api.clientes.Service.exception;

public class DataIntegrityException extends RuntimeException{
    public DataIntegrityException() {
        super();
    }

    public DataIntegrityException(String message) {
        super(message);
    }
}

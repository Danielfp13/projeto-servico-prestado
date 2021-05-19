package com.api.clientes.Service.exception;

public class ObjectNotFoundException extends RuntimeException{
    public ObjectNotFoundException() {
        super();
    }

    public ObjectNotFoundException(String message) {
        super(message);
    }
}

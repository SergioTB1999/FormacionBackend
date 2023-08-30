package com.bosonit.trip_backend.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class UnprocessableEntityException extends RuntimeException{
    public UnprocessableEntityException(String mensaje){
        super(mensaje);
    }
}

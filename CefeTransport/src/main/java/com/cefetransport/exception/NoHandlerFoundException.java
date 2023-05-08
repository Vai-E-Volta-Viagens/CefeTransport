package com.cefetransport.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import jakarta.servlet.ServletException;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoHandlerFoundException extends ServletException {
 
    public NoHandlerFoundException() {

        super("Página não encontrada");

    }

}
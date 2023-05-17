package com.cefetransport.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UsuarioNaoLogadoException extends RuntimeException {
    
    public UsuarioNaoLogadoException() {

        super("Usuário não logado");

    }

}
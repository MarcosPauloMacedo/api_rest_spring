package br.edu.imepac.atendimento.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundExceptions extends RuntimeException {
    
    public NotFoundExceptions(String message) {
        super(message);
    }
}

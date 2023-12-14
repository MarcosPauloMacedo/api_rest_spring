package br.edu.imepac.atendimento.exceptions;

import java.sql.Date;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandleExceptions {
    
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleConstraintViolationException(ConstraintViolationException e) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setStatus(HttpStatus.BAD_REQUEST.value());
        errorMessage.setMensagem( e.getConstraintViolations().stream().findFirst().get().getMessage());
        errorMessage.setData(new Date(System.currentTimeMillis()));
        errorMessage.setCaminho("/atendimento");
        errorMessage.setDescricao(e.getMessage());

        return errorMessage;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {

        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setStatus(HttpStatus.BAD_REQUEST.value());
        errorMessage.setMensagem("O corpo da requisição está inválido, Verifique os campos e tente novamente");
        errorMessage.setData(new Date(System.currentTimeMillis()));
        errorMessage.setCaminho("/atendimento");
        errorMessage.setDescricao(e.getMessage());

        return errorMessage;
    }
}

package com.project.imunipet.exception.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.project.imunipet.exception.PetNotFoundException;

@RestControllerAdvice
public class PetNotFoundAdvice {

    @ExceptionHandler(PetNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String petNotFoundHandler(PetNotFoundException exception) {
        return exception.getMessage();
    }
}

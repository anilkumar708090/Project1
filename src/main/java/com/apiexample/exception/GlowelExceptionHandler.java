package com.apiexample.exception;

import com.apiexample.dto.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@ControllerAdvice
public class GlowelExceptionHandler {


    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ErrorDetails> handleResoourceNotfoundException(ResourceNotFound ex){

        ErrorDetails err= new  ErrorDetails(ex.getMessage(), new Date());
        return  new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);


    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity< ErrorDetails> handleException(Exception ex){

        ErrorDetails err= new  ErrorDetails(ex.getMessage(), new Date());
        return  new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);


    }

}

package com.thierno.gestion_boutique.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalHandler {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handle(RuntimeException runtimeException){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(runtimeException.getMessage());
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handle(MethodArgumentNotValidException methodArgumentNotValidException){
        var errorResponse = new HashMap<String,String>();
        methodArgumentNotValidException.getBindingResult().getAllErrors()
                .forEach(error->{
                    var fieldName= ((FieldError) error).getField();
                    var errorMessage = error.getDefaultMessage();
                    errorResponse.put(fieldName,errorMessage);
                    errorResponse.put("message", error.getObjectName());
                });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}

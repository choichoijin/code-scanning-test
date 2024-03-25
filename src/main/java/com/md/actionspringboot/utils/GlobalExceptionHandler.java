package com.md.actionspringboot.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BindException.class)
    public GlobalResponse handleBindException(BindException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        List<String> messages = new ArrayList<>();
        for (FieldError error :bindingResult.getFieldErrors()){
            String message = error.getDefaultMessage();
            messages.add(message);
        }
        return GlobalResponse.builder()
                .status("failed").message(messages.get(0)).build();
    }

    @ExceptionHandler(RuntimeException.class)
    public GlobalResponse handleRuntimeException(RuntimeException ex) {
        String message = ex.getMessage();
        return GlobalResponse.builder()
                .status("failed").message(message).build();
    }

}

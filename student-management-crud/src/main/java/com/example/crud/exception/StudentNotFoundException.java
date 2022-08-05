package com.example.crud.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class StudentNotFoundException extends RuntimeException{
    public static final long serialVersionId = 1L;
    public StudentNotFoundException(String err){
        super(err);
    }
}

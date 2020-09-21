package com.example.MySpringApplicationWithDB.exceptions;

import org.springframework.http.HttpStatus;

public class NotFoundException extends Exception{

    public NotFoundException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    public HttpStatus getStatus(){
        return HttpStatus.NOT_FOUND;
    }
}

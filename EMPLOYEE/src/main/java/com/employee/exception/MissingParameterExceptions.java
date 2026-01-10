package com.employee.exception;

import org.springframework.http.HttpStatus;

public class MissingParameterExceptions extends  RuntimeException{

    private  String message ;
    private HttpStatus httpStatus ;

    public MissingParameterExceptions(String message) {
        this.message = message;
        this.httpStatus = HttpStatus.BAD_REQUEST;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}

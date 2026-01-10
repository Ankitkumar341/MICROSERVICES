package com.employee.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends RuntimeException{

    private String Message ;
    private HttpStatus httpStatus ;

    public BadRequestException(String message) {
        Message = message;
        this.httpStatus = HttpStatus.BAD_REQUEST;
    }

    @Override
    public String getMessage() {
        return Message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}

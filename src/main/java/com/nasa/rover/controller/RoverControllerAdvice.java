package com.nasa.rover.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

@ControllerAdvice
public class RoverControllerAdvice {

    @ExceptionHandler(NullPointerException.class)
    public HttpStatus invalidDateRangeViolation(final NullPointerException ex) {
        return HttpStatus.BAD_REQUEST;
    }

    @ExceptionHandler(IOException.class)
    public HttpStatus invalidSourceUrlViolation(final IOException ex) {
        return HttpStatus.NOT_FOUND;
    }
}

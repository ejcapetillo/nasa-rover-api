package com.nasa.rover.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RoverControllerAdvice {

    @ExceptionHandler(NullPointerException.class)
    public HttpStatus invalidDateRangeViolation(final NullPointerException ex) {
        return HttpStatus.NOT_FOUND;
    }
}

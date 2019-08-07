package com.nasa.rover.model;

import org.springframework.http.HttpStatus;

public class Response {

    private HttpStatus httpStatus;
    private Integer photoCount;
    private String errorMessage;

    public Response() {

    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(final HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public Integer getPhotoCount() {
        return photoCount;
    }

    public void setPhotoCount(final Integer photoCount) {
        this.photoCount = photoCount;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}

package com.nasa.rover.model;

import org.springframework.http.HttpStatus;

public class Response {

    private HttpStatus httpStatus;
    private Integer photoCount;

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
}

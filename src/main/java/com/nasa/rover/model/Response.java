package com.nasa.rover.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Response {

    private List<Photo> photos;

    public Response() {

    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(final List<Photo> photos) {
        this.photos = photos;
    }
}

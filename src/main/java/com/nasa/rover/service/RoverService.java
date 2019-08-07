package com.nasa.rover.service;

import com.nasa.rover.model.Photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RoverService {

    private RestTemplate restTemplate;

    @Autowired
    public RoverService(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<Photo> getPhotos(final String date) {
        final String nasaURL = "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?earth_date={date}";
        return restTemplate.getForEntity(nasaURL, Photo.class, date);
    }

}

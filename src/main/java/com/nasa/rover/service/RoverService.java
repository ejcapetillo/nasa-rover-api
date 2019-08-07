package com.nasa.rover.service;

import com.nasa.rover.model.PhotoWrapper;
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

    public ResponseEntity<PhotoWrapper> getPhotos(final String date) {
        final String apiKey = "DEMO_KEY";
        final String nasaURL = "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?earth_date={date}&api_key={apiKey}";
        return restTemplate.getForEntity(nasaURL, PhotoWrapper.class, date, apiKey);
    }

}

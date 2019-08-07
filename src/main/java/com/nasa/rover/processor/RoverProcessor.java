package com.nasa.rover.processor;

import com.nasa.rover.model.Photo;
import com.nasa.rover.model.Response;
import com.nasa.rover.service.RoverService;
import com.nasa.rover.validator.DateValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoverProcessor {

    private final DateValidator dateValidator;
    private final RoverService roverService;

    public RoverProcessor(final DateValidator dateValidator, final RoverService roverService) {
        this.dateValidator = dateValidator;
        this.roverService = roverService;
    }


    public void getPhotos(final String date) {
        if (dateValidator.isDateValid(date)) {
            final ResponseEntity<Response> response = roverService.getPhotos(date);
            createPhotoDirectory(date, response.getBody().getPhotos());
        }
    }

    private void createPhotoDirectory(final String date, final List<Photo> photoList) {

    }
}

package com.nasa.rover.processor;

import com.nasa.rover.service.RoverService;
import com.nasa.rover.validator.DateValidator;
import org.springframework.stereotype.Service;

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
            roverService.getPhotos(date);
        }
    }
}

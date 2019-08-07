package com.nasa.rover.controller;

import com.nasa.rover.model.Response;
import com.nasa.rover.processor.RoverProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/photos")
public class RoverController {

    private final RoverProcessor roverProcessor;

    @Autowired
    public RoverController(final RoverProcessor roverProcessor) {
        this.roverProcessor = roverProcessor;
    }

    /**
     * Endpoint to return photos from NASA's Mars rover given a date in the YYYY-MM-dd format
     * @param date Date to return photos from
     * @return HTTP Status and number of photos to be saved, if applicable
     */
    @GetMapping
    public Response getPhotos(@RequestParam(value = "date") final String date) {
        return roverProcessor.getPhotos(date);
    }


}

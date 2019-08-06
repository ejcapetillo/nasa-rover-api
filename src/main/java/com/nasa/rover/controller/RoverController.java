package com.nasa.rover.controller;

import com.nasa.rover.processor.RoverProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/rover")
public class RoverController {

    private final RoverProcessor roverProcessor;

    @Autowired
    public RoverController(final RoverProcessor roverProcessor) {
        this.roverProcessor = roverProcessor;
    }

    @GetMapping
    public HttpStatus getPhotos(final String date) {
        roverProcessor.getPhotos(date);
        return HttpStatus.OK;
    }
}

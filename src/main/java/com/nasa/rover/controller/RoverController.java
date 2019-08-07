package com.nasa.rover.controller;

import com.nasa.rover.processor.RoverProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoverController {

    private final RoverProcessor roverProcessor;

    @Autowired
    public RoverController(final RoverProcessor roverProcessor) {
        this.roverProcessor = roverProcessor;
    }

    @GetMapping("/photos")
    public HttpStatus getPhotos(@RequestParam(value = "date") final String date) {
        roverProcessor.getPhotos(date);
        return HttpStatus.OK;
    }


}

package com.nasa.rover.service;

import com.nasa.rover.model.Photo;
import com.nasa.rover.model.PhotoWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class RoverServiceTest {

    @InjectMocks
    private RoverService roverService;

    @Spy
    private RestTemplate restTemplate;

    @Test
    public void testValidDay() {
        final ResponseEntity<PhotoWrapper> wrapperResponseEntity = roverService.getPhotos("2015-06-03");
        assertNotNull(wrapperResponseEntity.getBody());
        final List<Photo> photoList = wrapperResponseEntity.getBody().getPhotos();
        assertEquals(4, photoList.size());
    }
}

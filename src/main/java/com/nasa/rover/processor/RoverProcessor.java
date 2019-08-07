package com.nasa.rover.processor;

import com.nasa.rover.model.Photo;
import com.nasa.rover.model.Response;
import com.nasa.rover.service.RoverService;
import com.nasa.rover.validator.DateValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.util.List;

@Service
public class RoverProcessor {

    private final DateValidator dateValidator;
    private final RoverService roverService;

    public RoverProcessor(final DateValidator dateValidator, final RoverService roverService) {
        this.dateValidator = dateValidator;
        this.roverService = roverService;
    }


    public void getPhotos(final String date) throws IOException {
        if (dateValidator.isDateValid(date)) {
            final ResponseEntity<Response> response = roverService.getPhotos(date);
            final List<Photo> photoList = response.getBody().getPhotos();

            for (final Photo photo : photoList) {
                downloadPicture(date, photo.getImgSrc());
            }
        }
    }

    private void downloadPicture(final String date, final String url) throws IOException {
        final String fileName = url.substring(url.lastIndexOf("/") + 1);
        final ReadableByteChannel readableByteChannel = Channels.newChannel(new URL(url).openStream());
        final FileOutputStream fileOutputStream = new FileOutputStream(fileName);
        final FileChannel fileChannel = fileOutputStream.getChannel();
        fileChannel.transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
    }
}

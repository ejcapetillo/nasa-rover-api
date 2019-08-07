package com.nasa.rover.processor;

import com.nasa.rover.model.Photo;
import com.nasa.rover.model.PhotoWrapper;
import com.nasa.rover.model.Response;
import com.nasa.rover.service.RoverService;
import com.nasa.rover.validator.DateValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.util.List;
import java.util.Objects;

@Service
public class RoverProcessor {

    private final DateValidator dateValidator;
    private final RoverService roverService;

    public RoverProcessor(final DateValidator dateValidator, final RoverService roverService) {
        this.dateValidator = dateValidator;
        this.roverService = roverService;
    }


    public Response getPhotos(final String date) throws IOException {
        final Response response = new Response();
        response.setHttpStatus(HttpStatus.OK);

        if (dateValidator.isDateValid(date)) {
            final ResponseEntity<PhotoWrapper> responseEntity = roverService.getPhotos(date);
            final List<Photo> photoList = Objects.requireNonNull(responseEntity.getBody()).getPhotos();
            if (photoList != null && !photoList.isEmpty()) {
                response.setPhotoCount(photoList.size());
                downloadPicture(date, photoList);
            }
        } else {
            response.setHttpStatus(HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    private void downloadPicture(final String date, final List<Photo> photoList) throws IOException {
        final File path = new File(date);
        final boolean isDirectoryCreated = path.exists() || path.mkdir();

        if (isDirectoryCreated) {
            for (final Photo photo : photoList) {
                final String url = photo.getImgSrc();
                final String fileName = date + url.substring(url.lastIndexOf("/"));
                final ReadableByteChannel readableByteChannel = Channels.newChannel(new URL(url).openStream());
                final FileOutputStream fileOutputStream = new FileOutputStream(fileName);
                final FileChannel fileChannel = fileOutputStream.getChannel();
                fileChannel.transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
            }
        } else {
            throw new IOException("Path could not be created");
        }
    }
}

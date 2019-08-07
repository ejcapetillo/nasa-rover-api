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


    /**
     * Method to begin the process of validation, retrieval, and downloading of photos from the NASA API
     * @param date Date to return photos from
     * @return response containing the HTTP status and the number of photos saved
     */
    public Response getPhotos(final String date) {
        final Response response = new Response();
        response.setHttpStatus(HttpStatus.OK);

        if (dateValidator.isDateValid(date)) {
            final ResponseEntity<PhotoWrapper> responseEntity = roverService.getPhotos(date);
            final List<Photo> photoList = Objects.requireNonNull(responseEntity.getBody()).getPhotos();
            if (photoList != null && !photoList.isEmpty()) {
                response.setPhotoCount(photoList.size());
                try {
                    downloadPicture(date, photoList, response);
                } catch (final IOException ex) {
                    response.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
                    response.setErrorMessage(ex.getMessage());
                }
            }
        } else {
            response.setHttpStatus(HttpStatus.BAD_REQUEST);
            response.setErrorMessage("Invalid Date Given (Must be YYY-MM-dd)");
        }
        return response;
    }


    /**
     * Method to begin downloading photos from the NASA API
     * @param date Date to be used as the Path name
     * @param photoList List of photo URLs retrieved from the NASA API
     * @throws IOException IOException thrown during photo download phase
     */
    private void downloadPicture(final String date, final List<Photo> photoList, final Response response) throws IOException {
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
            response.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setErrorMessage("Unable to create save directory");
        }
    }
}

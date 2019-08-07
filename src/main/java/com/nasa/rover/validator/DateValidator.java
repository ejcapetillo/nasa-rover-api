package com.nasa.rover.validator;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class DateValidator {

    public boolean isDateValid(final String date) {
        if (date == null) {
            return false;
        } else {
            final LocalDate convertedDate = LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
            return !convertedDate.isAfter(LocalDate.now());
        }
    }
}

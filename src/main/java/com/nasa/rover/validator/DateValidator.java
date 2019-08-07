package com.nasa.rover.validator;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class DateValidator {

    /**
     * Method to validate that the date String can be converted to the proper format and isn't a date in the future
     * @param date Date string to validate
     * @return boolean indicating if the date string was valid or not
     */
    public boolean isDateValid(final String date) {
        if (date == null || date.isEmpty()) {
            return false;
        } else {
            final LocalDate convertedDate = LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
            return !convertedDate.isAfter(LocalDate.now());
        }
    }
}

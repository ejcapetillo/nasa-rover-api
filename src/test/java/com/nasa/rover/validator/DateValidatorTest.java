package com.nasa.rover.validator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;


@RunWith(MockitoJUnitRunner.class)
public class DateValidatorTest {

    @InjectMocks
    private DateValidator dateValidator;

    @Test
    public void testNullDate() {
        assertFalse(dateValidator.isDateValid(null));
    }

    @Test
    public void testEmptyDate() {
        assertFalse(dateValidator.isDateValid(""));
    }

    @Test
    public void testFutureDate() {
        final String date = LocalDate.now().plusDays(1).toString();
        assertFalse(dateValidator.isDateValid(date));
    }

    @Test
    public void testToday() {
        assertTrue(dateValidator.isDateValid(LocalDate.now().format(DateTimeFormatter.ISO_DATE)));
    }
}

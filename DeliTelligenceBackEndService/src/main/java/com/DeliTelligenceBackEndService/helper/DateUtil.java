package com.DeliTelligenceBackEndService.helper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtil {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;

    /**
     * Parses a date string into a LocalDate object using the ISO_LOCAL_DATE format (YYYY-MM-DD).
     *
     * @param dateStr The date string to be parsed.
     * @return The corresponding LocalDate object.
     * @throws IllegalArgumentException If the date string is not in the valid format.
     */
    public static LocalDate parseDate(String dateStr) {
        try {
            return LocalDate.parse(dateStr, FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Expected format is YYYY-MM-DD.", e);
        }
    }
}

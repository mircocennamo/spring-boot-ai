package it.interno.ai.utils;

/**
 * @author mirco.cennamo on 01/03/2025
 * @project spring-into-ai
 */
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtil {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;

    public static LocalDate parseDate(String dateStr) {
        try {
            return LocalDate.parse(dateStr, DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            // Handle the exception, e.g., log the error or return a default value
            return null;
        }
    }

    public static String formatDate(LocalDate date) {
        return date.format(DATE_FORMATTER);
    }
}

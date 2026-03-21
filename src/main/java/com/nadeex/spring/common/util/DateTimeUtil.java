package com.nadeex.spring.common.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Utility class for date and time operations.
 * All operations are UTC-based unless a ZoneId is explicitly provided.
 */
public final class DateTimeUtil {

    private DateTimeUtil() {
        throw new UnsupportedOperationException("Utility class");
    }

    private static final ZoneId UTC = ZoneId.of("UTC");

    /**
     * Returns the current UTC instant.
     */
    public static Instant now() {
        return Instant.now();
    }

    /**
     * Formats an Instant using the given pattern in UTC.
     */
    public static String format(Instant instant, String pattern) {
        if (instant == null) return null;
        return DateTimeFormatter.ofPattern(pattern)
                .withZone(UTC)
                .format(instant);
    }

    /**
     * Parses a date string to an Instant using the given pattern in UTC.
     */
    public static Instant parse(String dateString, String pattern) {
        if (dateString == null || dateString.isBlank()) return null;
        return DateTimeFormatter.ofPattern(pattern)
                .withZone(UTC)
                .parse(dateString, Instant::from);
    }

    /**
     * Returns the start of the day (00:00:00) for the given instant in UTC.
     */
    public static Instant startOfDay(Instant instant) {
        if (instant == null) return null;
        return instant.atZone(UTC).toLocalDate().atStartOfDay(UTC).toInstant();
    }

    /**
     * Returns the end of the day (23:59:59.999999999) for the given instant in UTC.
     */
    public static Instant endOfDay(Instant instant) {
        if (instant == null) return null;
        LocalDate date = instant.atZone(UTC).toLocalDate();
        return date.plusDays(1).atStartOfDay(UTC).toInstant().minusNanos(1);
    }

    /**
     * Returns true if the given instant is between start (inclusive) and end (inclusive).
     */
    public static boolean isBetween(Instant instant, Instant start, Instant end) {
        if (instant == null || start == null || end == null) return false;
        return !instant.isBefore(start) && !instant.isAfter(end);
    }

    /**
     * Returns an Instant from epoch milliseconds.
     */
    public static Instant fromEpochMilli(long epochMilli) {
        return Instant.ofEpochMilli(epochMilli);
    }
}


package com.nadeex.spring.common.constants;

/**
 * Common regular expression patterns for input validation across all services.
 */
public final class RegexPatterns {

    private RegexPatterns() {
        throw new UnsupportedOperationException("Utility class");
    }

    /** RFC 5322 simplified email pattern. */
    public static final String EMAIL = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

    /** E.164 international phone number format. */
    public static final String PHONE = "^\\+?[1-9]\\d{1,14}$";

    /** Standard UUID format (version 1-5). */
    public static final String UUID  = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$";

    /** Letters and digits only. */
    public static final String ALPHANUMERIC = "^[a-zA-Z0-9]+$";

    /** Slug pattern for URL-friendly identifiers. */
    public static final String SLUG = "^[a-z0-9]+(?:-[a-z0-9]+)*$";
}


package com.nadeex.spring.common.util;

import com.nadeex.spring.common.constants.RegexPatterns;

/**
 * Utility class for common validation operations.
 */
public final class ValidationUtil {

    private ValidationUtil() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Returns true if the email matches a valid format.
     */
    public static boolean isValidEmail(String email) {
        if (email == null || email.isBlank()) return false;
        return email.matches(RegexPatterns.EMAIL);
    }

    /**
     * Returns true if the phone number matches E.164 format.
     */
    public static boolean isValidPhone(String phone) {
        if (phone == null || phone.isBlank()) return false;
        return phone.matches(RegexPatterns.PHONE);
    }

    /**
     * Returns true if the string matches standard UUID format.
     */
    public static boolean isValidUUID(String uuid) {
        if (uuid == null || uuid.isBlank()) return false;
        return uuid.matches(RegexPatterns.UUID);
    }

    /**
     * Throws {@link IllegalArgumentException} if the object is null.
     */
    public static void requireNonNull(Object obj, String message) {
        if (obj == null) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Throws {@link IllegalArgumentException} if the string is null or blank.
     */
    public static void requireNonEmpty(String str, String message) {
        if (str == null || str.isBlank()) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Throws {@link IllegalArgumentException} if the condition is false.
     */
    public static void requireTrue(boolean condition, String message) {
        if (!condition) {
            throw new IllegalArgumentException(message);
        }
    }
}


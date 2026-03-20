package com.nadeex.spring.common.util;

/**
 * Utility class for common string manipulation operations.
 */
public final class StringUtil {

    private StringUtil() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Returns true if the string is null or empty.
     */
    public static boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    /**
     * Returns true if the string is not null and not empty.
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * Returns true if the string is null, empty, or only whitespace.
     */
    public static boolean isBlank(String str) {
        return str == null || str.isBlank();
    }

    /**
     * Truncates the string to maxLength, appending "..." if truncated.
     */
    public static String truncate(String str, int maxLength) {
        if (str == null) return null;
        if (str.length() <= maxLength) return str;
        return str.substring(0, Math.max(0, maxLength - 3)) + "...";
    }

    /**
     * Capitalizes the first letter of the string.
     */
    public static String capitalize(String str) {
        if (isEmpty(str)) return str;
        return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }

    /**
     * Converts a snake_case or kebab-case string to camelCase.
     */
    public static String toCamelCase(String str) {
        if (isEmpty(str)) return str;
        String[] parts = str.split("[_\\-]");
        StringBuilder sb = new StringBuilder(parts[0].toLowerCase());
        for (int i = 1; i < parts.length; i++) {
            sb.append(capitalize(parts[i].toLowerCase()));
        }
        return sb.toString();
    }

    /**
     * Converts a camelCase string to snake_case.
     */
    public static String toSnakeCase(String str) {
        if (isEmpty(str)) return str;
        return str.replaceAll("([a-z])([A-Z])", "$1_$2").toLowerCase();
    }

    /**
     * Returns the string or a default value if null/empty.
     */
    public static String defaultIfEmpty(String str, String defaultValue) {
        return isEmpty(str) ? defaultValue : str;
    }
}


package com.nadeex.spring.common;

import com.nadeex.spring.common.response.ApiResponse;
import com.nadeex.spring.common.response.PagedResponse;
import com.nadeex.spring.common.exception.ErrorCode;
import com.nadeex.spring.common.util.StringUtil;
import com.nadeex.spring.common.util.ValidationUtil;
import com.nadeex.spring.common.util.DateTimeUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("nadeex-spring-common Tests")
class CommonLibraryTest {

    // ── ApiResponse ────────────────────────────────────────────────────────────
    @Nested
    @DisplayName("ApiResponse")
    class ApiResponseTest {

        @Test
        void success_withData_shouldReturnSuccessResponse() {
            ApiResponse<String> response = ApiResponse.success("payload");
            assertTrue(response.isSuccess());
            assertEquals("payload", response.getData());
            assertNotNull(response.getTimestamp());
            assertNull(response.getMessage());
        }

        @Test
        void success_withDataAndMessage_shouldReturnSuccessResponse() {
            ApiResponse<String> response = ApiResponse.success("payload", "Created");
            assertTrue(response.isSuccess());
            assertEquals("payload", response.getData());
            assertEquals("Created", response.getMessage());
        }

        @Test
        void error_shouldReturnFailureResponse() {
            ApiResponse<Void> response = ApiResponse.error("Something went wrong");
            assertFalse(response.isSuccess());
            assertEquals("Something went wrong", response.getMessage());
            assertNull(response.getData());
        }
    }

    // ── PagedResponse ──────────────────────────────────────────────────────────
    @Nested
    @DisplayName("PagedResponse")
    class PagedResponseTest {

        @Test
        void of_shouldCalculateTotalPagesAndFlags() {
            PagedResponse<String> response = PagedResponse.of(List.of("a", "b"), 0, 2, 5L);
            assertEquals(0, response.getPage());
            assertEquals(2, response.getSize());
            assertEquals(5L, response.getTotalElements());
            assertEquals(3, response.getTotalPages());
            assertTrue(response.isFirst());
            assertFalse(response.isLast());
        }

        @Test
        void of_lastPage_shouldSetLastTrue() {
            PagedResponse<String> response = PagedResponse.of(List.of("e"), 2, 2, 5L);
            assertFalse(response.isFirst());
            assertTrue(response.isLast());
        }

        @Test
        void of_zeroSize_shouldReturnZeroPages() {
            PagedResponse<String> response = PagedResponse.of(List.of(), 0, 0, 0L);
            assertEquals(0, response.getTotalPages());
        }
    }

    // ── ErrorCode ──────────────────────────────────────────────────────────────
    @Nested
    @DisplayName("ErrorCode")
    class ErrorCodeTest {

        @Test
        void getCode_shouldReturnStringValue() {
            assertEquals("RESOURCE_NOT_FOUND", ErrorCode.RESOURCE_NOT_FOUND.getCode());
            assertEquals("UNAUTHORIZED", ErrorCode.UNAUTHORIZED.getCode());
        }
    }

    // ── StringUtil ─────────────────────────────────────────────────────────────
    @Nested
    @DisplayName("StringUtil")
    class StringUtilTest {

        @Test
        void isEmpty_nullOrEmpty_returnsTrue() {
            assertTrue(StringUtil.isEmpty(null));
            assertTrue(StringUtil.isEmpty(""));
            assertFalse(StringUtil.isEmpty("a"));
        }

        @Test
        void truncate_exceedsMax_appendsEllipsis() {
            assertEquals("Hello...", StringUtil.truncate("Hello World", 8));
        }

        @Test
        void truncate_withinMax_returnsOriginal() {
            assertEquals("Hi", StringUtil.truncate("Hi", 10));
        }

        @Test
        void capitalize_shouldUppercaseFirst() {
            assertEquals("Hello", StringUtil.capitalize("hello"));
        }

        @Test
        void toCamelCase_snakeCase_convertedCorrectly() {
            assertEquals("helloWorld", StringUtil.toCamelCase("hello_world"));
        }

        @Test
        void toSnakeCase_camelCase_convertedCorrectly() {
            assertEquals("hello_world", StringUtil.toSnakeCase("helloWorld"));
        }
    }

    // ── ValidationUtil ─────────────────────────────────────────────────────────
    @Nested
    @DisplayName("ValidationUtil")
    class ValidationUtilTest {

        @Test
        void isValidEmail_valid_returnsTrue() {
            assertTrue(ValidationUtil.isValidEmail("user@example.com"));
        }

        @Test
        void isValidEmail_invalid_returnsFalse() {
            assertFalse(ValidationUtil.isValidEmail("not-an-email"));
            assertFalse(ValidationUtil.isValidEmail(null));
        }

        @Test
        void isValidPhone_e164_returnsTrue() {
            assertTrue(ValidationUtil.isValidPhone("+94771234567"));
        }

        @Test
        void isValidUUID_valid_returnsTrue() {
            assertTrue(ValidationUtil.isValidUUID("550e8400-e29b-41d4-a716-446655440000"));
        }

        @Test
        void requireNonNull_null_throwsIllegalArgumentException() {
            assertThrows(IllegalArgumentException.class,
                    () -> ValidationUtil.requireNonNull(null, "must not be null"));
        }

        @Test
        void requireNonEmpty_blank_throwsIllegalArgumentException() {
            assertThrows(IllegalArgumentException.class,
                    () -> ValidationUtil.requireNonEmpty("  ", "must not be empty"));
        }
    }

    // ── DateTimeUtil ───────────────────────────────────────────────────────────
    @Nested
    @DisplayName("DateTimeUtil")
    class DateTimeUtilTest {

        @Test
        void now_shouldReturnCurrentInstant() {
            Instant before = Instant.now();
            Instant now = DateTimeUtil.now();
            Instant after = Instant.now();
            assertFalse(now.isBefore(before));
            assertFalse(now.isAfter(after));
        }

        @Test
        void isBetween_withinRange_returnsTrue() {
            Instant start = Instant.parse("2026-01-01T00:00:00Z");
            Instant end   = Instant.parse("2026-12-31T23:59:59Z");
            Instant mid   = Instant.parse("2026-06-15T12:00:00Z");
            assertTrue(DateTimeUtil.isBetween(mid, start, end));
        }

        @Test
        void isBetween_outsideRange_returnsFalse() {
            Instant start = Instant.parse("2026-01-01T00:00:00Z");
            Instant end   = Instant.parse("2026-06-01T00:00:00Z");
            Instant after = Instant.parse("2026-12-01T00:00:00Z");
            assertFalse(DateTimeUtil.isBetween(after, start, end));
        }

        @Test
        void startOfDay_shouldReturnMidnight() {
            Instant instant = Instant.parse("2026-03-20T14:30:00Z");
            Instant start   = DateTimeUtil.startOfDay(instant);
            assertEquals(Instant.parse("2026-03-20T00:00:00Z"), start);
        }
    }
}


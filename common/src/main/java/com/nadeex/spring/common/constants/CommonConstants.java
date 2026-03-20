package com.nadeex.spring.common.constants;

/**
 * Application-wide constants shared across all nadeex libraries and consuming services.
 */
public final class CommonConstants {

    private CommonConstants() {
        throw new UnsupportedOperationException("Utility class");
    }

    // ── HTTP Headers ───────────────────────────────────────────────────────────
    public static final String HEADER_CORRELATION_ID = "X-Correlation-ID";
    public static final String HEADER_TENANT_ID      = "X-Tenant-ID";
    public static final String HEADER_USER_ID        = "X-User-ID";
    public static final String HEADER_API_VERSION    = "Accept";

    // ── Date / Time ────────────────────────────────────────────────────────────
    public static final String DATE_FORMAT     = "yyyy-MM-dd";
    public static final String DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    public static final String TIMEZONE_UTC    = "UTC";

    // ── Pagination ─────────────────────────────────────────────────────────────
    public static final int    DEFAULT_PAGE_SIZE      = 20;
    public static final int    MAX_PAGE_SIZE          = 100;
    public static final String DEFAULT_SORT_FIELD     = "createdAt";
    public static final String DEFAULT_SORT_DIRECTION = "DESC";

    // ── MDC Keys (used by nadeex-spring-logging) ───────────────────────────────
    public static final String MDC_CORRELATION_ID = "correlationId";
    public static final String MDC_TENANT_ID      = "tenantId";
    public static final String MDC_USER_ID        = "userId";
    public static final String MDC_REQUEST_METHOD = "requestMethod";
    public static final String MDC_REQUEST_URI    = "requestUri";
}


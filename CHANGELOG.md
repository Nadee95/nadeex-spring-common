# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

## [0.1.0] - 2026-04-03

### Added
- `ApiResponse<T>` — generic API response wrapper with `success`, `message`, `data`, and `timestamp` fields; static factory methods `success()` and `error()`
- `PagedResponse<T>` — pagination response with `content`, `page`, `size`, `totalElements`, `totalPages`, `first`, `last`; static `of()` factory
- `ErrorResponse` — structured error body with `status`, `error`, `message`, `path`, `timestamp`, `errorCode`, and nested `ValidationError` list
- `BaseException` — abstract base for all custom exceptions; carries an `ErrorCode` field
- `ErrorCode` — enum of application error codes: `INTERNAL_SERVER_ERROR`, `BAD_REQUEST`, `VALIDATION_ERROR`, `RESOURCE_NOT_FOUND`, `RESOURCE_ALREADY_EXISTS`, `UNAUTHORIZED`, `FORBIDDEN`, `INVALID_TOKEN`, `TOKEN_EXPIRED`, `BUSINESS_RULE_VIOLATION`, `OPERATION_NOT_ALLOWED`
- `BaseDto` — abstract `Serializable` base DTO with `@JsonInclude(NON_NULL)`
- `AuditableDto` — extends `BaseDto`; adds `createdBy`, `createdAt`, `updatedBy`, `updatedAt` audit fields
- `CommonConstants` — HTTP header name constants (`X-Correlation-ID`, `X-Tenant-ID`, `X-User-ID`), date/time format strings, pagination defaults (`DEFAULT_PAGE_SIZE=20`, `MAX_PAGE_SIZE=100`)
- `RegexPatterns` — constants for `EMAIL`, `PHONE` (E.164), `UUID`, `ALPHANUMERIC`
- `DateTimeUtil` — utility methods: `format()`, `parse()`, `now()`, `startOfDay()`, `endOfDay()`, `isBetween()`
- `StringUtil` — utility methods: `isEmpty()`, `isNotEmpty()`, `truncate()`, `capitalize()`, `toCamelCase()`, `toSnakeCase()`
- `ValidationUtil` — utility methods: `isValidEmail()`, `isValidPhone()`, `isValidUUID()`, `requireNonNull()`, `requireNonEmpty()`
- Spring Boot auto-configuration via `META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports`
- Gradle Kotlin DSL build with `java-library` + `maven-publish` plugins
- Local Maven (`mavenLocal`) and GitHub Packages publishing
- JaCoCo test coverage reporting (XML + HTML)
- GitHub Actions CI workflow (`ci.yml`) — build, test, coverage on `main`/`dev` push and PR
- GitHub Actions publish workflow (`publish.yml`) — triggered on GitHub Release creation

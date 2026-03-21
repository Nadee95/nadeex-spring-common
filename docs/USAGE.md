# Usage Guide

## Getting Started

Add `nadeex-spring-common` to your Spring Boot project dependencies.

### Gradle (Kotlin DSL)

```kotlin
dependencies {
    implementation("com.nadeex.spring:common:0.1.0")
}
```

### Maven

```xml
<dependency>
    <groupId>com.nadeex.spring</groupId>
    <artifactId>common</artifactId>
    <version>0.1.0</version>
</dependency>
```

## API Responses

Use `ApiResponse<T>` to return standardized responses from your controllers:

```java
@GetMapping("/{id}")
public ResponseEntity<ApiResponse<UserDto>> getUser(@PathVariable Long id) {
    UserDto user = userService.findById(id);
    return ResponseEntity.ok(ApiResponse.success(user));
}
```

## Pagination

Use `PagedResponse<T>` for paginated results:

```java
@GetMapping
public ResponseEntity<PagedResponse<UserDto>> listUsers(Pageable pageable) {
    Page<UserDto> page = userService.findAll(pageable);
    return ResponseEntity.ok(PagedResponse.of(page));
}
```

## Exception Handling

Extend `BaseException` for custom exceptions:

```java
public class UserNotFoundException extends BaseException {
    public UserNotFoundException(Long id) {
        super(ErrorCode.NOT_FOUND, "User not found: " + id);
    }
}
```

## DTOs

Extend `BaseDto` or `AuditableDto` for your data transfer objects:

```java
public class UserDto extends AuditableDto {
    private String username;
    private String email;
}
```

## Utilities

### DateTimeUtil

```java
String formatted = DateTimeUtil.format(LocalDateTime.now());
LocalDateTime parsed = DateTimeUtil.parse("2024-01-01T00:00:00");
```

### StringUtil

```java
boolean blank = StringUtil.isBlank(value);
String trimmed = StringUtil.trimToEmpty(value);
```

### ValidationUtil

```java
ValidationUtil.requireNonNull(value, "Value must not be null");
```

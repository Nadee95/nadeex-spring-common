# Examples

## Standardized API Response

```java
// Success response
ApiResponse<UserDto> response = ApiResponse.success(userDto);
// {"success": true, "data": {...}, "message": null}

// Error response
ApiResponse<Void> error = ApiResponse.error("Something went wrong");
// {"success": false, "data": null, "message": "Something went wrong"}
```

## Paged Response

```java
Page<UserDto> page = userRepository.findAll(pageable);
PagedResponse<UserDto> paged = PagedResponse.of(page);
// {
//   "content": [...],
//   "page": 0,
//   "size": 20,
//   "totalElements": 100,
//   "totalPages": 5,
//   "last": false
// }
```

## Custom Exception with Error Code

```java
// Define a custom exception
public class ResourceNotFoundException extends BaseException {
    public ResourceNotFoundException(String resource, Long id) {
        super(ErrorCode.NOT_FOUND, resource + " not found with id: " + id);
    }
}

// Throw it in a service
public UserDto findUser(Long id) {
    return userRepository.findById(id)
        .map(userMapper::toDto)
        .orElseThrow(() -> new ResourceNotFoundException("User", id));
}
```

## Controller with Full Response Handling

```java
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserDto>> getUser(@PathVariable Long id) {
        UserDto user = userService.findById(id);
        return ResponseEntity.ok(ApiResponse.success(user));
    }

    @GetMapping
    public ResponseEntity<PagedResponse<UserDto>> listUsers(Pageable pageable) {
        Page<UserDto> page = userService.findAll(pageable);
        return ResponseEntity.ok(PagedResponse.of(page));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UserDto>> createUser(@Valid @RequestBody CreateUserRequest request) {
        UserDto created = userService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(created));
    }
}
```

## Using Constants

```java
import com.nadeex.spring.common.constants.CommonConstants;
import com.nadeex.spring.common.constants.RegexPatterns;

// Use predefined regex patterns for validation
@Pattern(regexp = RegexPatterns.EMAIL)
private String email;

@Pattern(regexp = RegexPatterns.PHONE)
private String phone;
```

package staffs.identitymanagement.ui;


import io.github.bucket4j.Bucket;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import staffs.common.security.RateLimiterService;
import staffs.common.ui.CommonController;
import staffs.identitymanagement.application.AppUserApplicationService;
import staffs.identitymanagement.application.AppUserQueryHandler;
import staffs.identitymanagement.application.UserService;
import staffs.identitymanagement.domain.user.AppUserDomainException;

import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@ComponentScan(basePackages = {"staffs.common.security"})  //needed to locate RateLimiterService
@RestController
public class IdentityController extends CommonController {
    private UserService userService;
    private AppUserApplicationService appUserApplicationService;
    private AppUserQueryHandler queryHandler;
    private final RateLimiterService rateLimiterService;
    private final HttpServletRequest request;

    @PostMapping("/validate")
    public ResponseEntity<?> validate(@RequestBody UserDetailsRequest userDetailsRequest) {
        String ip = request.getRemoteAddr();
        Bucket bucket = rateLimiterService.resolveBucket(ip);

        if (bucket.tryConsume(1)) {
            Optional<String> token = userService.authenticate(userDetailsRequest);

            return token.<ResponseEntity<?>>map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                            .body(Map.of("error", "Invalid credentials")));
        }
        //number of attempts has been exceeded
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                .body(Map.of("error", "Too many attempts. Try again later."));//make parsing of responses easier
    }

    /**
     * GET /users
     * Returns all users
     */
    @GetMapping("/users")
    public Iterable<?> getAllUsers() {
        return queryHandler.findAllUsers();
    }

    /**
     * POST /users
     * Adds a new user
     */
    @PostMapping("/users/newUser")
    public HttpStatus addUser(@RequestBody AddNewAppUserCommand command) throws AppUserDomainException {
        appUserApplicationService.createAppUser(command);
        return HttpStatus.CREATED;
    }

    //we could add a refresh token and then also add invalidate token following that
}
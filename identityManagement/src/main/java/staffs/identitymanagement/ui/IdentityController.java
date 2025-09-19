package staffs.identitymanagement.ui;


import io.github.bucket4j.Bucket;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import staffs.common.security.RateLimiterService;
import staffs.common.ui.CommonController;
import staffs.identitymanagement.application.AppUserApplicationService;
import staffs.identitymanagement.application.AppUserQueryHandler;
import staffs.identitymanagement.application.AppUserService;
import staffs.identitymanagement.domain.user.AppUserDomainException;

import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@ComponentScan(basePackages = {"staffs.common.security"})  //needed to locate RateLimiterService
@RestController
public class IdentityController extends CommonController {
    private AppUserService appUserService;
    private final RateLimiterService rateLimiterService;
    private final HttpServletRequest request;
    private final AppUserApplicationService applicationService;
    private final AppUserQueryHandler queryHandler;


    @PostMapping("/validate")
    public ResponseEntity<?> validate(@RequestBody AppUserDetailsRequest appUserDetailsRequest) {
        String ip = request.getRemoteAddr();
        Bucket bucket = rateLimiterService.resolveBucket(ip);

        if (bucket.tryConsume(1)) {
            Optional<String> token = appUserService.authenticate(appUserDetailsRequest);

            return token.<ResponseEntity<?>>map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                            .body(Map.of("error", "Invalid credentials")));
        }
        //number of attempts has been exceeded
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                .body(Map.of("error", "Too many attempts. Try again later."));//make parsing of responses easier
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Iterable<?> getAllAppUsers() {
        return queryHandler.findAllUsers();
    }

    /**
     * POST /users
     * Adds a new user
     */
    @PostMapping("/newAppUser")
    public HttpStatus addAppUser(@RequestBody AddNewAppUserCommand command) throws AppUserDomainException {
        applicationService.createAppUser(command);
        return HttpStatus.CREATED;
    }

    /**
     * GET /users/{id}
     * Returns a user by ID
     */
//    @GetMapping("/{id}")
//    public ResponseEntity<UserDTO> getLeaveRequestById(@PathVariable String id) {
//             return queryHandler.findUserById(id)
//                 .map(ResponseEntity::ok)
//                 .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with ID: " + id));
//         }

//    /**
//     * DELETE /users/{id}
//     * Deletes a user by ID
//     */
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
//        applicationService.deleteUser(id);
//        return ResponseEntity.noContent().build();
//    }

    //we could add a refresh token and then also add invalidate token following that
}
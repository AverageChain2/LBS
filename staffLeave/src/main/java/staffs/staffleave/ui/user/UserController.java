package staffs.staffleave.ui.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import staffs.staffleave.application.user.DTO.UserDTO;
import staffs.staffleave.application.user.UserApplicationService;
import staffs.staffleave.application.user.UserQueryHandler;
import staffs.staffleave.domain.leaveRequest.LeaveRequestDomainException;
import staffs.staffleave.domain.user.UserDomainException;
import staffs.staffleave.infrastructure.user.UserJpa;
import staffs.staffleave.ui.LeaveRequest.AddNewLeaveRequestCommand;

import java.util.List;

@RestController
@RequestMapping("/users2")
@RequiredArgsConstructor
public class UserController {

    private final UserApplicationService applicationService;
    private final UserQueryHandler queryHandler;

    /**
     * GET /users
     * Returns all users
     */
    @GetMapping
    public Iterable<?> getAllUsers() {
        return queryHandler.findAllUsers();
    }

    /**
     * POST /users
     * Adds a new user
     */
    @PostMapping("/newUser")
    public HttpStatus addUser(@RequestBody AddNewUserCommand command) throws UserDomainException {
        applicationService.createUser(command);
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
}

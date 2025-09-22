package staffs.staffleave.ui.LeaveRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import staffs.staffleave.application.leaveRequest.LeaveRequestApplicationService;
import staffs.staffleave.application.leaveRequest.LeaveRequestQueryHandler;
import staffs.staffleave.domain.leaveRequest.LeaveRequestDomainException;

import java.util.Optional;

@RestController
@RequestMapping("/leaveRequests")
@RequiredArgsConstructor
public class LeaveRequestController {

    private final LeaveRequestQueryHandler queryHandler;
    private final LeaveRequestApplicationService applicationService;
    private final LeaveRequestApplicationService leaveRequestApplicationService;

    /**
     * GET /leaveRequests
     */
    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Iterable<?> getAllLeaveRequests() {
        return queryHandler.findAllLeaveRequests();
    }

    /**
     * GET /leaveRequests/{Id}
     */
    @GetMapping("/id/{Id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Optional<?> getLeaveRequestByID(@PathVariable String Id) {
        return queryHandler.findLeaveRequestById(Id);
    }

    /**
     * GET /leaveRequests/staff/{staffId}
     */
    @GetMapping("/staff/{staffId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Iterable<?> getLeaveRequestsByStaffID(@PathVariable String staffId) {
        return queryHandler.findLeaveRequestsByStaffId(staffId);
    }

    /**
     * GET /leaveRequests/{team}
     */
    @GetMapping("/team/{team}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Iterable<?> getLeaveRequestsByTeam(@PathVariable String team) {
        return queryHandler.findLeaveBalancesByTeam(team);
    }

    /**
     * POST /leave-requests
     * {
     *   "staffId": "86080e1c-8cb4-4777-9129-feaa485bbdfd",
     *   "startDate": "2025-08-10T09:00:00Z",
     *   "endDate": "2025-08-15T17:00:00Z",
     *   "leaveAmount": 5.0
     * }
     */
    @PostMapping("/newLeaveRequest")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER', 'STAFF')")
    public HttpStatus createLeaveRequest(@RequestBody AddNewLeaveRequestCommand command) throws LeaveRequestDomainException {
        applicationService.createLeaveRequest(command);
        return HttpStatus.CREATED;
    }

    // Optional: GET by ID
    // @GetMapping("/{id}")
    // public ResponseEntity<LeaveRequestDTO> getLeaveRequestById(@PathVariable String id) {
    //     return queryHandler.findLeaveRequestById(id)
    //         .map(ResponseEntity::ok)
    //         .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Leave request not found with ID: " + id));
    // }

    // Optional: DELETE
    // @DeleteMapping("/{id}")
    // public ResponseEntity<Void> deleteLeaveRequest(@PathVariable String id) {
    //     applicationService.deleteLeaveRequest(id);
    //     return ResponseEntity.noContent().build();
    // }

    @PostMapping("/updateStatus")
    public ResponseEntity<Void>  updateStatus(@RequestBody @Valid UpdateLeaveStatusCommand command)
            throws  LeaveRequestDomainException {
        leaveRequestApplicationService.updateStatus(command);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}

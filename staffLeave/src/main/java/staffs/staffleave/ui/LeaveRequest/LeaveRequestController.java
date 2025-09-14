package staffs.staffleave.ui.LeaveRequest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import staffs.staffleave.application.leaveApproval.LeaveApprovalApplicationService;
import staffs.staffleave.application.leaveRequest.LeaveRequestApplicationService;
import staffs.staffleave.application.leaveRequest.LeaveRequestQueryHandler;
import staffs.staffleave.domain.leaveApproval.LeaveApprovalDomainException;
import staffs.staffleave.domain.leaveRequest.LeaveRequestDomainException;
import staffs.staffleave.ui.leaveApproval.UpdateLeaveStatusCommand;

@RestController
@RequestMapping("/leaveRequests")
@RequiredArgsConstructor
public class LeaveRequestController {

    private final LeaveRequestQueryHandler queryHandler;
    private final LeaveRequestApplicationService applicationService;
    private final LeaveApprovalApplicationService leaveApprovalApplicationService;
    private final LeaveRequestApplicationService leaveRequestApplicationService;

    /**
     * GET /leave-requests
     */
    @GetMapping
    public Iterable<?> getAllLeaveRequests() {
        return queryHandler.findAllLeaveRequests();
    }

    /**
     * POST /leave-requests
     * Example JSON:
     * {
     *   "staffId": "86080e1c-8cb4-4777-9129-feaa485bbdfd",
     *   "startDate": "2025-08-10T09:00:00Z",
     *   "endDate": "2025-08-15T17:00:00Z",
     *   "leaveAmount": 5.0
     * }
     */
    @PostMapping("/newLeaveRequest")
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
    public HttpStatus updateStatus(@RequestBody UpdateLeaveStatusCommand command)
            throws  LeaveRequestDomainException {
        leaveRequestApplicationService.updateStatus(command);
        return HttpStatus.ACCEPTED;
    }
}

package staffs.leavemanagement.ui.leaveApproval;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import staffs.leavemanagement.application.leaveApproval.DTO.LeaveApprovalDTO;
import staffs.leavemanagement.application.leaveApproval.LeaveApprovalApplicationService;
import staffs.leavemanagement.application.leaveApproval.LeaveApprovalQueryHandler;
import staffs.leavemanagement.domain.leaveApproval.LeaveApprovalDomainException;

import java.util.List;

@RestController
@RequestMapping("/leave-approvals")
@RequiredArgsConstructor
public class LeaveApprovalController {

    private final LeaveApprovalQueryHandler queryHandler;
    private final LeaveApprovalApplicationService applicationService;

    /**
     * GET /leave-approvals
     */
    @GetMapping
    public Iterable<?> getAllApprovals() {
        return queryHandler.findAllLeaveApprovals();
    }

//    /**
//     * GET /leave-approvals/{id}
//     */
//    @GetMapping("/{id}")
//    public ResponseEntity<LeaveApprovalDTO> getApprovalById(@PathVariable String id) {
//        return queryHandler.findApprovalById(id)
//                .map(ResponseEntity::ok)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Leave approval not found with ID: " + id));
//    }
//
//    /**
//     * GET /leave-approvals/staff/{staffId}
//     */
//    @GetMapping("/staff/{staffId}")
//    public ResponseEntity<List<LeaveApprovalDTO>> getApprovalsByStaffId(@PathVariable String staffId) {
//        return queryHandler.findApprovalsByStaffId(staffId)
//                .map(ResponseEntity::ok)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No approvals found for staff ID: " + staffId));
//    }

    /**
     * POST /leave-approvals
     {
     "leaveId": "86080e1c-8cb4-4777-9129-feaa485bbdfd",
     "approverId": "86080e1c-8cb4-4777-9129-feaa485bbdfd",
     "status": "APPROVED",
     "reason": "Meets all leave policy requirements.",
     "approved_at": "2025-07-31T10:15:30Z"
     }

     */
    @PostMapping("/newLeaveApproval")
    public HttpStatus createLeaveApproval(@RequestBody AddNewLeaveApprovalCommand command) throws LeaveApprovalDomainException {
        applicationService.createLeaveApproval(command);
        return HttpStatus.CREATED;
    }

//    /**
//     * DELETE /leave-approvals/{id}
//     */
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteLeaveApproval(@PathVariable String id) {
//        applicationService.deleteLeaveApproval(id);
//        return ResponseEntity.noContent().build();
//    }
}

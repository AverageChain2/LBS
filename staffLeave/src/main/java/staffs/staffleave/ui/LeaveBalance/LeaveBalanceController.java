package staffs.staffleave.ui.LeaveBalance;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import staffs.staffleave.application.leaveBalance.LeaveBalanceApplicationService;
import staffs.staffleave.application.leaveBalance.LeaveBalanceQueryHandler;
import staffs.staffleave.domain.leaveBalance.LeaveBalanceDomainException;

@RestController
@RequestMapping("/leave-balances")
@RequiredArgsConstructor
public class LeaveBalanceController {

    private final LeaveBalanceQueryHandler queryHandler;
    private final LeaveBalanceApplicationService applicationService;

    /**
     * GET /leave-balances
     */
    @GetMapping
    public Iterable<?> getAllLeaveBalances() {
        return queryHandler.findAllLeaveBalances();
    }

    /**
     * POST /leave-balances
     * Example JSON:
     * {
     *   "staffId": "86080e1c-8cb4-4777-9129-feaa485bbdfd",
     *   "leaveYear": "2025",
     *   "balance": 20.0
     * }
     */
    @PostMapping("/newLeaveBalance")
    public HttpStatus createLeaveBalance(@RequestBody AddNewLeaveBalanceCommand command) throws LeaveBalanceDomainException {
        applicationService.createLeaveBalance(command);
        return HttpStatus.CREATED;
    }

    // Optional: GET by staff ID
    // @GetMapping("/staff/{staffId}")
    // public ResponseEntity<List<LeaveBalanceDTO>> getLeaveBalanceByStaffId(@PathVariable String staffId) {
    //     return queryHandler.findLeaveBalancesByStaffId(staffId)
    //         .map(ResponseEntity::ok)
    //         .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No leave balance found for staff ID: " + staffId));
    // }
}

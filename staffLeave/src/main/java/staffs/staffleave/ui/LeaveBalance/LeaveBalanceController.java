package staffs.staffleave.ui.LeaveBalance;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import staffs.staffleave.application.leaveBalance.LeaveBalanceApplicationService;
import staffs.staffleave.application.leaveBalance.LeaveBalanceQueryHandler;
import staffs.staffleave.domain.leaveBalance.LeaveBalanceDomainException;

import java.util.Optional;

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
     * {
     *   "staffId": "86080e1c-8cb4-4777-9129-feaa485bbdfd",
     *   "leaveYear": "2025",
     *   "balance": 20.0
     * }
     */
    @PostMapping("/newLeaveBalance")
    @PreAuthorize("hasAuthority('ADMIN')")
    public HttpStatus createLeaveBalance(@RequestBody AddNewLeaveBalanceCommand command) throws LeaveBalanceDomainException {
        applicationService.createLeaveBalance(command);
        return HttpStatus.CREATED;
    }

//    @PostMapping("/newLeaveBalance")
//    @PreAuthorize("hasAuthority('ADMIN')")
//    public HttpStatus updateCurrentLeaveBalance(@RequestBody AddNewLeaveBalanceCommand command) throws LeaveBalanceDomainException {
//        applicationService.updateLeaveBalance(command);
//        return HttpStatus.CREATED;
//    }

    // GET by staff ID
     @GetMapping("/staff/{staffId}")
     @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER', 'STAFF')")
     public Iterable<?> getLeaveBalanceByStaffId(@PathVariable String staffId) {
         return queryHandler.findLeaveBalancesByStaffId(staffId);
     }
    // GET by staff ID and year
    @GetMapping("/staff/{staffId}/year/{leaveYear}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER', 'STAFF')")
    public Optional<?> getLeaveBalanceByStaffId(@PathVariable String staffId, @PathVariable String leaveYear) {
        return queryHandler.findLeaveBalancesByStaffIdAndYear(staffId, leaveYear);
//             .map(ResponseEntity::ok)
//             .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No leave balance found for staff ID: " + staffId));
    }
}

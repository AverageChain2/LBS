package staffs.staffleave;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import staffs.common.domain.Identity;
import staffs.staffleave.domain.leaveBalance.LeaveBalance;
import staffs.staffleave.domain.leaveRequest.LeaveRequest;
import staffs.staffleave.domain.leaveRequest.LeaveRequestDomainException;
import staffs.staffleave.domain.leaveRequest.LeaveStatus;
import staffs.staffleave.domain.events.LeaveStatusChangeEvent;
import staffs.staffleave.domain.events.LeaveBalanceUpdatedEvent;
import staffs.staffleave.infrastructure.user.UserJpa;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class LeaveRequestLeaveBalanceInteractionTests {

    private final Float INITIAL_BALANCE = 210.0f;

    @Test
    @DisplayName("Leave approval deducts balance and triggers domain events from both aggregates")
    void testLeaveApprovalEvents() throws LeaveRequestDomainException {
        Identity leaveId = new Identity("leave001");
        Identity balanceId = new Identity("bal001");
        UserJpa user = new UserJpa();

        LeaveBalance balance = LeaveBalance.leaveBalanceOf(
                balanceId, "staff123", "2025", INITIAL_BALANCE
        );

        LeaveRequest request = LeaveRequest.leaveRequestOf(
                leaveId, user,
                new Date(2025 - 1900, 9, 1),
                new Date(2025 - 1900, 9, 3),
                7.5f,
                LeaveStatus.Pending,
                "Holiday"
        );



        // Approve request
        request.updateStatus(LeaveStatus.Approved, "Approved by manager");
        balance.updateBalance(balance.balance() - request.leaveAmount());

        // Assertions
        assertEquals(LeaveStatus.Approved, request.status());
        assertEquals(INITIAL_BALANCE - 7.5f, balance.balance());

        // LeaveRequest domain event
        assertFalse(request.listOfDomainEvents().isEmpty(), "LeaveRequest should have domain events");
        assertTrue(request.listOfDomainEvents().get(0) instanceof LeaveStatusChangeEvent, "Expected LeaveStatusChangeEvent");

        LeaveStatusChangeEvent leaveEvent = (LeaveStatusChangeEvent) request.listOfDomainEvents().get(0);
        assertEquals(leaveId, leaveEvent.getAggregateID());
        assertEquals(LeaveStatus.Approved, leaveEvent.getStatus());
        assertEquals("Approved by manager", leaveEvent.getReason());

        // LeaveBalance domain event
        assertFalse(balance.listOfDomainEvents().isEmpty(), "LeaveBalance should have domain events");
        assertTrue(balance.listOfDomainEvents().get(0) instanceof LeaveBalanceUpdatedEvent, "Expected LeaveBalanceUpdatedEvent");

        LeaveBalanceUpdatedEvent balanceEvent = (LeaveBalanceUpdatedEvent) balance.listOfDomainEvents().get(0);
        assertEquals(balanceId.id(), balanceEvent.getLeaveBalanceId());
        assertEquals("staff123", balanceEvent.getStaffId());
        assertEquals("2025", balanceEvent.getLeaveYear());
        assertEquals(INITIAL_BALANCE- 7.5f, balanceEvent.getNewBalance());
    }

    @Test
    @DisplayName("Cancel adds balance and triggers event")
    void testLeaveCancelEvents() throws LeaveRequestDomainException {
        Identity leaveId = new Identity("leave001");
        Identity balanceId = new Identity("bal001");
        UserJpa user = new UserJpa();

        LeaveBalance balance = LeaveBalance.leaveBalanceOf(
                balanceId, "staff1234", "2025", INITIAL_BALANCE
        );

        LeaveRequest request = LeaveRequest.leaveRequestOf(
                leaveId, user,
                new Date(2025, 9, 1),
                new Date(2025, 9, 3),
                7.5f,
                LeaveStatus.Pending,
                "Holiday"
        );






        // Simulate cancel and balance update
        request.updateStatus(LeaveStatus.Cancelled, "No longer needed");
        balance.updateBalance(balance.balance() - request.leaveAmount());

        // Assertions
        assertEquals(LeaveStatus.Cancelled, request.status());
        assertEquals(INITIAL_BALANCE, balance.balance());

        // LeaveRequest domain event
        assertFalse(request.listOfDomainEvents().isEmpty(), "LeaveRequest should have domain events");
        assertTrue(request.listOfDomainEvents().get(0) instanceof LeaveStatusChangeEvent, "Expected LeaveStatusChangeEvent");

        LeaveStatusChangeEvent leaveCancelledEvent = (LeaveStatusChangeEvent) request.listOfDomainEvents().get(0);
        assertEquals(leaveId, leaveCancelledEvent.getAggregateID());
        assertEquals(LeaveStatus.Cancelled, leaveCancelledEvent.getStatus());
        assertEquals("Approved by manager", leaveCancelledEvent.getReason());

        // LeaveBalance domain event
        assertFalse(balance.listOfDomainEvents().isEmpty(), "LeaveBalance should have domain events");
        assertTrue(balance.listOfDomainEvents().get(0) instanceof LeaveBalanceUpdatedEvent, "Expected LeaveBalanceUpdatedEvent");

        LeaveBalanceUpdatedEvent balanceAddEvent = (LeaveBalanceUpdatedEvent) balance.listOfDomainEvents().get(0);
        assertEquals(balanceId.id(), balanceAddEvent.getLeaveBalanceId());
        assertEquals("staff123", balanceAddEvent.getStaffId());
        assertEquals("2025", balanceAddEvent.getLeaveYear());
        assertEquals(INITIAL_BALANCE + 7.5f, balanceAddEvent.getNewBalance());
    }
}

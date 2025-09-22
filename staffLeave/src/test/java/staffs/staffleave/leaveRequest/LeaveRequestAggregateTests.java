package staffs.staffleave.leaveRequest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import staffs.common.domain.Identity;
import staffs.staffleave.domain.leaveRequest.LeaveRequest;
import staffs.staffleave.domain.leaveRequest.LeaveRequestDomainException;
import staffs.staffleave.domain.leaveRequest.LeaveStatus;
import staffs.staffleave.infrastructure.user.UserJpa;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class LeaveRequestAggregateTests {

    private final Identity VALID_ID = new Identity("leave001");
    private final UserJpa VALID_USER = new UserJpa(); // Assuming default constructor is fine
    private final Date VALID_START_DATE = new Date(2025 - 1900, 9, 1); // 1st Oct 2025
    private final Date VALID_END_DATE = new Date(2025 - 1900, 9, 5);   // 5th Oct 2025
    private final Float VALID_LEAVE_AMOUNT = 4.0f;
    private final LeaveStatus INITIAL_STATUS = LeaveStatus.Pending;
    private final String INITIAL_REASON = "Annual leave";

    @Test
    @DisplayName("LeaveRequest is created successfully with valid details")
    void test01_createLeaveRequest() {
        LeaveRequest request = LeaveRequest.leaveRequestOf(
                VALID_ID, VALID_USER, VALID_START_DATE, VALID_END_DATE,
                VALID_LEAVE_AMOUNT, INITIAL_STATUS, INITIAL_REASON
        );

        assertNotNull(request);
        assertEquals(VALID_ID, request.id());
        assertEquals(VALID_USER, request.staffID());
        assertEquals(VALID_START_DATE, request.startDate());
        assertEquals(VALID_END_DATE, request.endDate());
        assertEquals(VALID_LEAVE_AMOUNT, request.leaveAmount());
        assertEquals(INITIAL_STATUS, request.status());
        assertEquals(INITIAL_REASON, request.reason());
    }

    @Test
    @DisplayName("Status update should change status and reason")
    void test02_updateStatus() throws LeaveRequestDomainException {
        LeaveRequest request = LeaveRequest.leaveRequestOf(
                VALID_ID, VALID_USER, VALID_START_DATE, VALID_END_DATE,
                VALID_LEAVE_AMOUNT, LeaveStatus.Pending, "Initial reason"
        );

        request.updateStatus(LeaveStatus.Approved, "Approved by manager");

        assertEquals(LeaveStatus.Approved, request.status());
        assertEquals("Approved by manager", request.reason());
    }

    @Test
    @DisplayName("Status update should not change anything if status is the same")
    void test03_noChangeOnSameStatus() throws LeaveRequestDomainException {
        LeaveRequest request = LeaveRequest.leaveRequestOf(
                VALID_ID, VALID_USER, VALID_START_DATE, VALID_END_DATE,
                VALID_LEAVE_AMOUNT, LeaveStatus.Pending, "Initial reason"
        );

        request.updateStatus(LeaveStatus.Pending, "Still pending");

        assertEquals(LeaveStatus.Pending, request.status());
        assertEquals("Initial reason", request.reason()); // Reason should remain unchanged
    }
}

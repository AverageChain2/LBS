package staffs.staffleave.leaveBalance;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import staffs.common.domain.Identity;
import staffs.staffleave.domain.events.LeaveBalanceUpdatedEvent;
import staffs.staffleave.domain.leaveBalance.LeaveBalance;

import static org.junit.jupiter.api.Assertions.*;

public class LeaveBalanceAggregateTests {

    private final Identity VALID_ID = new Identity("bal001");
    private final String VALID_STAFF_ID = "staff123";
    private final String VALID_LEAVE_YEAR = "2025";
    private final Float INITIAL_BALANCE = 210.0f;

    @Test
    @DisplayName("LeaveBalance is created successfully with valid details")
    void test01_createLeaveBalance() {
        LeaveBalance balance = LeaveBalance.leaveBalanceOf(
                VALID_ID, VALID_STAFF_ID, VALID_LEAVE_YEAR, INITIAL_BALANCE
        );

        assertNotNull(balance);
        assertEquals(VALID_ID, balance.id());
        assertEquals(VALID_STAFF_ID, balance.staffID());
        assertEquals(VALID_LEAVE_YEAR, balance.leaveYear());
        assertEquals(INITIAL_BALANCE, balance.balance());
    }

    @Test
    @DisplayName("Deducting leave reduces the balance correctly")
    void test02_deductLeave() {
        LeaveBalance balance = LeaveBalance.leaveBalanceOf(
                VALID_ID, VALID_STAFF_ID, VALID_LEAVE_YEAR, INITIAL_BALANCE
        );

        balance.updateBalance(INITIAL_BALANCE - 3.0f);

        assertEquals(7.0f, balance.balance());
    }

    @Test
    @DisplayName("Updating to a negative balance fails")
    void test03_deductTooMuchLeave() {
        LeaveBalance balance = LeaveBalance.leaveBalanceOf(
                VALID_ID, VALID_STAFF_ID, VALID_LEAVE_YEAR, INITIAL_BALANCE
        );

        assertThrows(IllegalArgumentException.class, () -> balance.updateBalance(-15.0f));
    }


        @Test
        @DisplayName("Updating leave balance should trigger LeaveBalanceUpdatedEvent")
        void testLeaveBalanceUpdateTriggersEvent() {
            Identity id = new Identity("bal001");
            LeaveBalance balance = LeaveBalance.leaveBalanceOf(id, "staff123", "2025", 10.0f);

            balance.updateBalance(INITIAL_BALANCE - 7.0f);

            assertEquals(INITIAL_BALANCE - 7.0f, balance.balance());
            assertFalse(balance.listOfDomainEvents().isEmpty());
            assertTrue(balance.listOfDomainEvents().get(0) instanceof LeaveBalanceUpdatedEvent);

            LeaveBalanceUpdatedEvent event = (LeaveBalanceUpdatedEvent) balance.listOfDomainEvents().get(0);
            assertEquals(id.id(), event.getLeaveBalanceId());
            assertEquals("staff123", event.getStaffId());
            assertEquals("2025", event.getLeaveYear());
            assertEquals(INITIAL_BALANCE - 7.0f, event.getNewBalance());
        }




}

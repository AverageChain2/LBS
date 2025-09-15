package staffs.staffleave.domain.leaveBalance;

import lombok.ToString;
import staffs.common.domain.Entity;
import staffs.common.domain.Identity;
import staffs.staffleave.domain.events.LeaveApprovedEvent;
import staffs.staffleave.domain.events.LeaveCancelledEvent;
import staffs.staffleave.domain.events.LeaveRejectedEvent;
import staffs.staffleave.domain.leaveApproval.LeaveStatus;

@ToString
public class LeaveBalance extends Entity {

    private String staffId;
    private final String leaveYear;
    private Float balance;

    public LeaveBalance(Identity id, String staffId, String leaveYear, Float balance) {
        super(id);
        setStaffId(staffId);
        this.leaveYear = leaveYear;
        this.balance = balance;
    }

    public void updateBalance(Float balance) {
        // Update fields
        this.balance = balance;
    }

    // Factory method for testing
    public static LeaveBalance leaveBalanceOf(Identity id, String staffId, String leaveYear, Float balance) {
        return new LeaveBalance(id, staffId, leaveYear, balance);
    }

    private void setStaffId(String staffId) {
        assertArgumentNotEmpty(staffId, "Staff ID cannot be empty");
        this.staffId = staffId;
    }

    // Domain methods
    public Identity id() {
        return id;
    }

    public String staffID() {
        return staffId;
    }

    public String leaveYear() {
        return leaveYear;
    }

    public Float balance() {
        return balance;
    }
}

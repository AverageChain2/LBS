package staffs.staffleave.domain.leaveRequest;

import lombok.ToString;
import staffs.common.domain.Entity;
import staffs.common.domain.Identity;

import java.util.Date;

@ToString
public class LeaveRequest extends Entity {

    private String staffId;
    private final Date startDate;
    private final Date endDate;
    private final Float leaveAmount;

    public LeaveRequest(Identity id, String staffId, Date startDate, Date endDate, Float leaveAmount) {
        super(id);
        setStaffId(staffId);
        this.startDate = startDate;
        this.endDate = endDate;
        this.leaveAmount = leaveAmount;
    }

    // Factory method for testing
    public static LeaveRequest leaveRequestOf(Identity id, String staffId, Date startDate, Date endDate, Float leaveAmount) {
        return new LeaveRequest(id, staffId, startDate, endDate, leaveAmount);
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

    public Date startDate() {
        return startDate;
    }

    public Date endDate() {
        return endDate;
    }

    public Float leaveAmount() {
        return leaveAmount;
    }
}

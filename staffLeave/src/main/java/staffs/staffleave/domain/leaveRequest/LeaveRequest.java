package staffs.staffleave.domain.leaveRequest;

import lombok.ToString;
import staffs.common.domain.Entity;
import staffs.common.domain.Identity;
import staffs.staffleave.infrastructure.user.UserJpa;

import java.util.Date;

@ToString
public class LeaveRequest extends Entity {

    private UserJpa staffID;
    private final Date startDate;
    private final Date endDate;
    private final Float leaveAmount;

    public LeaveRequest(Identity id, UserJpa staffID, Date startDate, Date endDate, Float leaveAmount) {
        super(id);
        setStaffId(staffID);
        this.startDate = startDate;
        this.endDate = endDate;
        this.leaveAmount = leaveAmount;
    }

    // Factory method for testing
    public static LeaveRequest leaveRequestOf(Identity id, UserJpa staffID, Date startDate, Date endDate, Float leaveAmount) {
        return new LeaveRequest(id, staffID, startDate, endDate, leaveAmount);
    }

    private void setStaffId(UserJpa staffID) {
        assertArgumentNotEmpty(staffID, "Staff ID cannot be empty");
        this.staffID = staffID;
    }

    // Domain methods
    public Identity id() {
        return id;
    }

    public UserJpa staffID() {
        return staffID;
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

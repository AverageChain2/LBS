package staffs.staffleave.domain.leaveRequest;

import lombok.ToString;
import staffs.common.domain.Entity;
import staffs.common.domain.Identity;
import staffs.common.security.AppUserJpa;

import staffs.staffleave.domain.events.LeaveStatusChangeEvent;

import java.util.Date;

@ToString
public class LeaveRequest extends Entity {

    private AppUserJpa staffID;
    private final Date startDate;
    private final Date endDate;
    private final Float leaveAmount;
    private LeaveStatus status;
    private String reason;

    public LeaveRequest(Identity id, AppUserJpa staffID, Date startDate, Date endDate, Float leaveAmount, LeaveStatus status, String reason) {
        super(id);
        setStaffId(staffID);
        this.startDate = startDate;
        this.endDate = endDate;
        this.leaveAmount = leaveAmount;
        this.status = status;
        this.reason = reason;

        // Remove leaveAmount due to pending request to prevent over spending leave
        addDomainEvent(new LeaveStatusChangeEvent(id, staffID, leaveAmount, status, ""));

    }

    public void updateStatus(LeaveStatus newStatus, String newReason) {
        if (this.status != newStatus) {
            if (this.status != LeaveStatus.Pending && this.status != LeaveStatus.Approved) {
                addDomainEvent(new LeaveStatusChangeEvent(id, staffID, leaveAmount, newStatus, newReason));
            }
            // Update fields
            this.status = newStatus;
            this.reason = newReason;
        }




    }

    // Factory method for testing
    public static LeaveRequest leaveRequestOf(Identity id, AppUserJpa staffID, Date startDate, Date endDate, Float leaveAmount, LeaveStatus status, String reason) {
        return new LeaveRequest(id, staffID, startDate, endDate, leaveAmount, status, reason);
    }

    private void setStaffId(AppUserJpa staffID) {
        assertArgumentNotEmpty(staffID, "Staff ID cannot be empty");
        this.staffID = staffID;
    }

    // Domain methods
    public Identity id() {
        return id;
    }

    public AppUserJpa staffID() {
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

    public LeaveStatus status() {
        return status;
    }

    public String reason() {
        return reason;
    }

}

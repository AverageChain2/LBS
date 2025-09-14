package staffs.staffleave.domain.leaveRequest;

import lombok.ToString;
import staffs.common.domain.Entity;
import staffs.common.domain.Identity;
import staffs.staffleave.domain.leaveApproval.LeaveStatus;
import staffs.staffleave.domain.leaveApproval.events.LeaveApprovedEvent;
import staffs.staffleave.domain.leaveApproval.events.LeaveCancelledEvent;
import staffs.staffleave.domain.leaveApproval.events.LeaveRejectedEvent;
import staffs.staffleave.infrastructure.user.UserJpa;

import java.util.Date;

@ToString
public class LeaveRequest extends Entity {

    private UserJpa staffID;
    private final Date startDate;
    private final Date endDate;
    private final Float leaveAmount;
    private LeaveStatus status;
    private String reason;

    public LeaveRequest(Identity id, UserJpa staffID, Date startDate, Date endDate, Float leaveAmount, LeaveStatus status, String reason) {
        super(id);
        setStaffId(staffID);
        this.startDate = startDate;
        this.endDate = endDate;
        this.leaveAmount = leaveAmount;
        this.status = status;
        this.reason = reason;
    }

    public void updateStatus(LeaveStatus newStatus, String newReason) {
        if (this.status != newStatus) {
            switch (newStatus) {
                case Approved -> addDomainEvent(new LeaveApprovedEvent(id, newReason));
                case Rejected -> addDomainEvent(new LeaveRejectedEvent(id, newReason));
                case Cancelled -> addDomainEvent(new LeaveCancelledEvent(id));
            }
        }

        // Update fields
        this.status = newStatus;
        this.reason = newReason;

    }

    // Factory method for testing
    public static LeaveRequest leaveRequestOf(Identity id, UserJpa staffID, Date startDate, Date endDate, Float leaveAmount, LeaveStatus status, String reason) {
        return new LeaveRequest(id, staffID, startDate, endDate, leaveAmount, status, reason);
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

    public LeaveStatus status() {
        return status;
    }

    public String reason() {
        return reason;
    }

}

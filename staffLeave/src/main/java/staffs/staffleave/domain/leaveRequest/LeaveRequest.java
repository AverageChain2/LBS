package staffs.staffleave.domain.leaveRequest;

import lombok.ToString;
import staffs.common.domain.Entity;
import staffs.common.domain.Identity;
import staffs.staffleave.domain.events.LeaveStatusChangeEvent;
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

        // Remove leaveAmount due to pending request
        addDomainEvent(new LeaveStatusChangeEvent(id, staffID, leaveAmount, status, status, ""));

    }

    public void updateStatus(LeaveStatus newStatus, String newReason) throws LeaveRequestDomainException {
        try {
            if (this.status == LeaveStatus.Cancelled) {
                throw new IllegalStateException("Cannot change status: request is already cancelled.");
            }
            if (this.status == LeaveStatus.Rejected && newStatus == LeaveStatus.Pending) {
                throw new IllegalStateException("Cannot change status: Cannot return status to pending.");
            }
            if (this.status == LeaveStatus.Approved && newStatus == LeaveStatus.Pending) {
                throw new IllegalStateException("Cannot change status: Cannot return status to pending.");
            }
            if (this.status != newStatus) {
                addDomainEvent(new LeaveStatusChangeEvent(id, staffID, leaveAmount, this.status, newStatus, newReason));
                this.status = newStatus;
                this.reason = newReason;
            } else {
                throw new IllegalStateException("Cannot change status: status is the same.");
            }
        }catch (IllegalArgumentException e) {
            throw new LeaveRequestDomainException(e.getMessage());
        }






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

package staffs.staffleave.domain.leaveApproval;

import lombok.ToString;
import staffs.common.domain.Entity;
import staffs.common.domain.Identity;
import staffs.staffleave.infrastructure.leaveRequest.LeaveRequestJpa;
import staffs.staffleave.infrastructure.user.UserJpa;

import java.util.Date;

@ToString
public class LeaveApproval extends Entity {

    private LeaveRequestJpa leaveID;
    private UserJpa approverID;
    private final LeaveStatus status;
    private final String reason;
    private final Date approvedAt;

    public LeaveApproval(Identity id, LeaveRequestJpa leaveID, UserJpa approverID, LeaveStatus status, String reason, Date approvedAt) {
        super(id);
        setLeaveID(leaveID);
        setApproverID(approverID);
        this.status = status;
        this.reason = reason;
        this.approvedAt = approvedAt;
    }

    // Inherits equals (id)

    // Factory method for testing
    public static LeaveApproval leaveApprovalOf(Identity id, LeaveRequestJpa leaveID, UserJpa approverID, LeaveStatus status, String reason, Date approvedAt) {
        return new LeaveApproval(id, leaveID, approverID, status, reason, approvedAt);
    }

    private void setLeaveID(LeaveRequestJpa leaveID){
        assertArgumentNotEmpty(leaveID,"LeaveID cannot be empty");
        this.leaveID = leaveID;
    }

    private void setApproverID(UserJpa approverID){
        assertArgumentNotEmpty(approverID,"ApproverID cannot be empty");
        this.approverID = approverID;
    }

    // Domain methods to access attributes using domain language rather than get
    public Identity id() {
        return id;
    }

    public LeaveRequestJpa leaveID() {
        return leaveID;
    }

    public UserJpa approverID() {
        return approverID;
    }

    public LeaveStatus status() {
        return status;
    }

    public String reason() {
        return reason;
    }

    public Date approvedAt() {
        return approvedAt;
    }
}

package staffs.leavemanagement.domain.leaveApproval;

import lombok.ToString;
import staffs.common.domain.Entity;
import staffs.common.domain.Identity;

import java.util.Date;

@ToString
public class LeaveApproval extends Entity {

    private String leaveID;
    private String approverID;
    private final String status;
    private final String reason;
    private final Date approvedAt;

    public LeaveApproval(Identity id, String leaveID, String approverID, String status, String reason, Date approvedAt) {
        super(id);
        setLeaveID(leaveID);
        setApproverID(approverID);
        this.status = status;
        this.reason = reason;
        this.approvedAt = approvedAt;
    }

    // Inherits equals (id)

    // Factory method for testing
    public static LeaveApproval leaveApprovalOf(Identity id, String leaveID, String approverID, String status, String reason, Date approvedAt) {
        return new LeaveApproval(id, leaveID, approverID, status, reason, approvedAt);
    }

    private void setLeaveID(String leaveID){
        assertArgumentNotEmpty(leaveID,"LeaveID cannot be empty");
        this.leaveID = leaveID;
    }

    private void setApproverID(String approverID){
        assertArgumentNotEmpty(approverID,"ApproverID cannot be empty");
        this.approverID = approverID;
    }

    // Domain methods to access attributes using domain language rather than get
    public Identity id() {
        return id;
    }

    public String leaveID() {
        return leaveID;
    }

    public String approverID() {
        return approverID;
    }

    public String status() {
        return status;
    }

    public String reason() {
        return reason;
    }

    public Date approvedAt() {
        return approvedAt;
    }
}

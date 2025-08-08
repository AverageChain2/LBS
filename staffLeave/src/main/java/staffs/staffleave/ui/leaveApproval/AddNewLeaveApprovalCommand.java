package staffs.staffleave.ui.leaveApproval;

import lombok.AllArgsConstructor;
import lombok.Getter;
import staffs.staffleave.domain.leaveApproval.LeaveStatus;
import staffs.staffleave.infrastructure.leaveRequest.LeaveRequestJpa;
import staffs.staffleave.infrastructure.user.UserJpa;

import java.util.Date;

@Getter
@AllArgsConstructor
public class AddNewLeaveApprovalCommand {
     LeaveRequestJpa leaveId;
     UserJpa approverId;
     LeaveStatus status;
     String reason;
     Date approvedAt;

    public String toString(){

        return String.format("\nLeave: %s, Approver: %s, Status: %s, Reason: %s, Approved date/time: %s",
                leaveId,
                approverId,
                status,
                reason,
                approvedAt);
    }

//    // Optional: constructor without ID (for creation use cases)
//    public AddNewLeaveApprovalCommand(String leaveId, String approverId, String status, String reason, Date approvedAt) {
//        this.leaveId = leaveId;
//        this.approverId = approverId;
//        this.status = status;
//        this.reason = reason;
//        this.approvedAt = approvedAt;
//    }


}

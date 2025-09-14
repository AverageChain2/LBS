package staffs.staffleave.ui.leaveApproval;

import staffs.staffleave.domain.leaveApproval.LeaveStatus;
import staffs.staffleave.infrastructure.leaveRequest.LeaveRequestJpa;
import staffs.staffleave.infrastructure.user.UserJpa;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateLeaveStatusCommand {
    private String leaveRequestID;
    private LeaveStatus status;
    private String reason;
    private UserJpa approverId;
}


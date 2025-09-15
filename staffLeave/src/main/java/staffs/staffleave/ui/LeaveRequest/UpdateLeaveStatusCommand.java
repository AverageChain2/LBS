package staffs.staffleave.ui.LeaveRequest;

import staffs.staffleave.domain.leaveApproval.LeaveStatus;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateLeaveStatusCommand {
    private String leaveRequestID;
    private LeaveStatus status;
    private String reason;
}


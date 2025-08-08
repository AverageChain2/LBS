package staffs.staffleave.application.leaveApproval.DTO;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import staffs.staffleave.domain.leaveApproval.LeaveStatus;
import staffs.staffleave.infrastructure.leaveRequest.LeaveRequestJpa;
import staffs.staffleave.infrastructure.user.UserJpa;

import java.util.Date;

@AllArgsConstructor
@EqualsAndHashCode
@Getter
@ToString
public class LeaveApprovalDTO {
    private String id;
    private LeaveRequestJpa leaveId;
    private UserJpa approverId;
    private LeaveStatus status;
    private String reason;
    private Date approved_at;
}

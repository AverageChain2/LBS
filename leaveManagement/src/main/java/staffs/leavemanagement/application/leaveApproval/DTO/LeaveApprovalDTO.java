package staffs.leavemanagement.application.leaveApproval.DTO;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;

@AllArgsConstructor
@EqualsAndHashCode
@Getter
@ToString
public class LeaveApprovalDTO {
    private String id;
    private String leaveId;
    private String approverId;
    private String status;
    private String reason;
    private Date approved_at;
}

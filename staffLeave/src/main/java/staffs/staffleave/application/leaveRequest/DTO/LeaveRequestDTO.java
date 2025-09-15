package staffs.staffleave.application.leaveRequest.DTO;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import staffs.staffleave.domain.leaveApproval.LeaveStatus;

import java.util.Date;

@AllArgsConstructor
@EqualsAndHashCode
@Getter
@ToString
public class LeaveRequestDTO {
    private String id;
    private String staffId;
    private Date startDate;
    private Date endDate;
    private Float leaveAmount;
    private LeaveStatus status;
    private String reason;
}

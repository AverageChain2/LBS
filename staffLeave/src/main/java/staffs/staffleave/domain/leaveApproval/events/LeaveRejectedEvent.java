package staffs.staffleave.domain.leaveApproval.events;

import lombok.Getter;
import lombok.ToString;
import staffs.common.domain.Identity;
import staffs.common.events.LocalEvent;
import staffs.staffleave.infrastructure.user.UserJpa;

import java.time.LocalDate;

@Getter
@ToString
public class LeaveRejectedEvent implements LocalEvent {
    private final Identity aggregateID;
    private final String occurredOn;
    private final Identity leaveRequestID;
    private final UserJpa approver;
    private final String reason;

    public LeaveRejectedEvent(Identity aggregateID, Identity leaveRequestID, UserJpa approver, String reason) {
        this.aggregateID = aggregateID;
        this.leaveRequestID = leaveRequestID;
        this.approver = approver;
        this.reason = reason;
        this.occurredOn = LocalDate.now().toString();
    }
}

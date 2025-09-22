package staffs.staffleave.domain.events;

import lombok.Getter;
import lombok.ToString;
import staffs.common.domain.Identity;
import staffs.common.events.LocalEvent;
import staffs.staffleave.domain.leaveRequest.LeaveStatus;
import staffs.staffleave.infrastructure.user.UserJpa;

import java.time.LocalDate;

@Getter
@ToString
public class LeaveStatusChangeEvent implements LocalEvent {
    private final Identity aggregateID;
    private final UserJpa staffID;
    private final Float leaveAmount;
    private final String reason;
    private final LeaveStatus oldStatus;
    private final LeaveStatus status;
    private final String occurredOn;


    public LeaveStatusChangeEvent(Identity aggregateID, UserJpa staffID, Float leaveAmount, LeaveStatus oldStatus, LeaveStatus status, String reason) {
        this.aggregateID = aggregateID;
        this.staffID = staffID;
        this.leaveAmount = leaveAmount;
        this.reason = reason;
        this.oldStatus = oldStatus;
        this.status = status;
        this.occurredOn = LocalDate.now().toString();
    }
}


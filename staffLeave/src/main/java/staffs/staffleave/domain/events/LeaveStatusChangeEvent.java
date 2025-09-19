package staffs.staffleave.domain.events;

import lombok.Getter;
import lombok.ToString;
import staffs.common.domain.Identity;
import staffs.common.events.LocalEvent;
import staffs.common.security.AppUserJpa;
import staffs.staffleave.domain.leaveRequest.LeaveStatus;

import java.time.LocalDate;

@Getter
@ToString
public class LeaveStatusChangeEvent implements LocalEvent {
    private final Identity aggregateID;
    private final AppUserJpa staffID;
    private final Float leaveAmount;
    private final String reason;
    private final LeaveStatus status;
    private final String occurredOn;


    public LeaveStatusChangeEvent(Identity aggregateID, AppUserJpa staffID, Float leaveAmount, LeaveStatus status, String reason) {
        this.aggregateID = aggregateID;
        this.staffID = staffID;
        this.leaveAmount = leaveAmount;
        this.reason = reason;
        this.status = status;
        this.occurredOn = LocalDate.now().toString();
    }
}


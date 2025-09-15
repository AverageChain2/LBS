package staffs.staffleave.domain.events;

import lombok.Getter;
import lombok.ToString;
import staffs.common.domain.Identity;
import staffs.common.events.LocalEvent;
import staffs.staffleave.infrastructure.user.UserJpa;

import java.time.LocalDate;

@Getter
@ToString
public class LeaveCancelledEvent implements LocalEvent {
    private final Identity aggregateID;
    private final UserJpa staffID;
    private final Float leaveAmount;
    private final String reason;
    private final String occurredOn;

    public LeaveCancelledEvent(Identity aggregateID,  UserJpa staffID, Float leaveAmount, String reason) {
        this.aggregateID = aggregateID;
        this.staffID = staffID;
        this.leaveAmount = leaveAmount;
        this.reason = reason;
        this.occurredOn = LocalDate.now().toString();
    }
}

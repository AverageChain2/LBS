package staffs.staffleave.domain.events;

import lombok.Getter;
import lombok.ToString;
import staffs.common.domain.Identity;
import staffs.common.events.LocalEvent;
import staffs.staffleave.infrastructure.user.UserJpa;

import java.time.LocalDate;

@Getter
@ToString
public class LeaveApprovedEvent implements LocalEvent {
    private final Identity aggregateID;
    private final UserJpa staffID;
    private final String occurredOn;
    private final String reason;

    public LeaveApprovedEvent(Identity aggregateID, UserJpa staffID, String reason) {
        this.aggregateID = aggregateID;
        this.staffID = staffID;
        this.reason = reason;
        this.occurredOn = LocalDate.now().toString();
    }
}


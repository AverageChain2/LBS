package staffs.staffleave.domain.leaveApproval.events;

import lombok.Getter;
import lombok.ToString;
import staffs.common.domain.Identity;
import staffs.common.events.LocalEvent;

import java.time.LocalDate;

@Getter
@ToString
public class LeaveApprovedEvent implements LocalEvent {
    private final Identity aggregateID;
    private final String occurredOn;
    private final String reason;

    public LeaveApprovedEvent(Identity aggregateID, String reason) {
        this.aggregateID = aggregateID;
        this.reason = reason;
        this.occurredOn = LocalDate.now().toString();
    }
}


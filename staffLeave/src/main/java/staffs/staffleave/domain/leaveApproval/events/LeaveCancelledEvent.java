package staffs.staffleave.domain.leaveApproval.events;

import lombok.Getter;
import lombok.ToString;
import staffs.common.domain.Identity;
import staffs.common.events.LocalEvent;

import java.time.LocalDate;

@Getter
@ToString
public class LeaveCancelledEvent implements LocalEvent {
    private final Identity aggregateID;
    private final String occurredOn;
    private final Identity leaveRequestID;

    public LeaveCancelledEvent(Identity aggregateID, Identity leaveRequestID) {
        this.aggregateID = aggregateID;
        this.leaveRequestID = leaveRequestID;
        this.occurredOn = LocalDate.now().toString();
    }
}

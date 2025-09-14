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

    public LeaveCancelledEvent(Identity aggregateID) {
        this.aggregateID = aggregateID;
        this.occurredOn = LocalDate.now().toString();
    }
}

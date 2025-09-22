package staffs.staffleave.domain.events;


import lombok.Getter;
import lombok.ToString;
import staffs.common.events.LocalEvent;

import java.time.LocalDate;

@Getter
@ToString
public class LeaveBalanceUpdatedEvent implements LocalEvent {

    private String occurredOn;
    private  String leaveBalanceId;
    private  String staffId;
    private  String leaveYear;
    private  Float newBalance;

    public LeaveBalanceUpdatedEvent(String leaveBalanceId, String staffId, String leaveYear, Float newBalance) {
        this.occurredOn = LocalDate.now().toString();//Otherwise exception when converting to JSON
        this.leaveBalanceId = leaveBalanceId;
        this.staffId = staffId;
        this.leaveYear = leaveYear;
        this.newBalance = newBalance;
    }

}

package staffs.staffleave.ui.LeaveRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class AddNewLeaveRequestCommand {
    private String staffId;
    private Date startDate;
    private Date endDate;
    private Float leaveAmount;

    @Override
    public String toString() {
        return String.format(
                "\nStaff ID: %s Start Date: %s, End Date: %s, Leave Amount: %.2f",
                staffId,
                startDate,
                endDate,
                leaveAmount
        );
    }
}

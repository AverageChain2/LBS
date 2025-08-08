package staffs.staffleave.ui.LeaveRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import staffs.staffleave.infrastructure.user.UserJpa;

import java.util.Date;

@Getter
@AllArgsConstructor
public class AddNewLeaveRequestCommand {
    private UserJpa staffId;
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

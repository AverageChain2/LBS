package staffs.staffleave.ui.LeaveBalance;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class AddNewLeaveBalanceCommand {
    private String staffId;
    private String leaveYear;
    private Float balance;

    @Override
    public String toString() {
        return String.format(
                "\nStaff ID: %s LeaveYear: %s, Balance: %.2f",
                staffId,
                leaveYear,
                balance

        );
    }
}

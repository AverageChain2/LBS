package staffs.staffleave.application.leaveBalance;


import staffs.staffleave.application.leaveBalance.DTO.LeaveBalanceDTO;
import staffs.staffleave.infrastructure.leaveBalance.LeaveBalanceJpa;

public class LeaveBalanceMapper {

    public static LeaveBalanceDTO toLeaveBalanceDTO(LeaveBalanceJpa leaveBalance) {
        return new LeaveBalanceDTO(
                leaveBalance.getId(),
                leaveBalance.getStaffId(),
                leaveBalance.getYear(),
                leaveBalance.getBalance()
        );
    }
}

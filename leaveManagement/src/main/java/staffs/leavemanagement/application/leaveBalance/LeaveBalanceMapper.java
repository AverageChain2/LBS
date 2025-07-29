package staffs.leavemanagement.application.leaveBalance;


import staffs.leavemanagement.application.leaveBalance.DTO.LeaveBalanceDTO;
import staffs.leavemanagement.infrastructure.leaveBalance.LeaveBalanceJpa;

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

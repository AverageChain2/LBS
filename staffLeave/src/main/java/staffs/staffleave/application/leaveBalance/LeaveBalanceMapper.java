package staffs.staffleave.application.leaveBalance;


import staffs.common.domain.Identity;
import staffs.staffleave.application.leaveBalance.DTO.LeaveBalanceDTO;
import staffs.staffleave.domain.leaveBalance.LeaveBalance;
import staffs.staffleave.infrastructure.leaveBalance.LeaveBalanceJpa;
import staffs.staffleave.infrastructure.leaveBalance.LeaveBalanceJpa;

public class LeaveBalanceMapper {

    public static LeaveBalanceDTO toLeaveBalanceDTO(LeaveBalanceJpa leaveBalance) {
        return new LeaveBalanceDTO(
                leaveBalance.getId(),
                leaveBalance.getStaffId(),
                leaveBalance.getLeaveYear(),
                leaveBalance.getBalance()
        );
    }

    // Domain to JPA
    public static LeaveBalanceJpa toJpa(LeaveBalance leaveBalance) {
        LeaveBalanceJpa jpa = new LeaveBalanceJpa();
        jpa.setId(leaveBalance.id().id());
        jpa.setStaffId(leaveBalance.staffID());
        jpa.setLeaveYear(leaveBalance.leaveYear());
        jpa.setBalance(leaveBalance.balance());
        return jpa;
    }

    // JPA to Domain
    public static LeaveBalance toDomain(LeaveBalanceJpa jpa) {
        return new LeaveBalance(
                new Identity(jpa.getId()),
                jpa.getStaffId(),
                jpa.getLeaveYear(),
                jpa.getBalance()
        );
    }
}

package staffs.staffleave.application.leaveBalance;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import staffs.staffleave.domain.events.LeaveStatusChangeEvent;
import staffs.staffleave.domain.leaveBalance.LeaveBalance;
import staffs.staffleave.infrastructure.leaveBalance.LeaveBalanceJpa;
import staffs.staffleave.infrastructure.leaveBalance.LeaveBalanceRepository;

import java.time.Year;
import java.util.Optional;

@Component
@AllArgsConstructor
public class LeaveStatusChangeDomainEventMapper {
    private final LeaveBalanceRepository leaveBalanceRepository;

    public LeaveBalance getLeaveBalanceFromStatusChangeEvent(LeaveStatusChangeEvent event){

        Optional<LeaveBalanceJpa> leaveBalanceJpa = leaveBalanceRepository.findBalanceByStaffIdAndYear(event.getStaffID().getId(), Year.now().toString());


        if (leaveBalanceJpa.isPresent()) {
            return LeaveBalanceMapper.toDomain(leaveBalanceJpa.get());
        }
        else{
            //Error
            return null;
        }
    }


}

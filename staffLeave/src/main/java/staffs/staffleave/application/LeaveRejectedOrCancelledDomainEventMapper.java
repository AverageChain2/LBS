package staffs.staffleave.application;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import staffs.staffleave.application.leaveBalance.LeaveBalanceMapper;
import staffs.staffleave.domain.events.LeaveCancelledEvent;
import staffs.staffleave.domain.leaveBalance.LeaveBalance;
import staffs.staffleave.infrastructure.leaveBalance.LeaveBalanceJpa;
import staffs.staffleave.infrastructure.leaveBalance.LeaveBalanceRepository;

import java.time.Year;
import java.util.Optional;

@Component
@AllArgsConstructor
public class LeaveRejectedOrCancelledDomainEventMapper {
    private final LeaveBalanceRepository leaveBalanceRepository;

    public LeaveBalance getLeaveBalanceFromCancelledEvent(LeaveCancelledEvent event){

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

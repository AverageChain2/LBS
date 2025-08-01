package staffs.staffleave.application.leaveBalance;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import staffs.staffleave.application.leaveBalance.DTO.LeaveBalanceDTO;
import staffs.staffleave.application.leaveBalance.LeaveBalanceMapper;
import staffs.staffleave.infrastructure.leaveBalance.LeaveBalanceJpa;
import staffs.staffleave.infrastructure.leaveBalance.LeaveBalanceRepository;

import java.util.Optional;

@AllArgsConstructor
@Service
public class LeaveBalanceQueryHandler {
    private LeaveBalanceRepository leaveBalanceRepository;

    //Not recommended to have this method as we could have a VERY big response
    public Iterable<LeaveBalanceJpa> findAllLeaveBalances(){

        return leaveBalanceRepository.findAll();
    }

    public Optional<LeaveBalanceDTO> findLeaveBalanceById(String leaveBalance_id) {
        return leaveBalanceRepository.findById(leaveBalance_id)
                .map(LeaveBalanceMapper::toLeaveBalanceDTO);
    }

}

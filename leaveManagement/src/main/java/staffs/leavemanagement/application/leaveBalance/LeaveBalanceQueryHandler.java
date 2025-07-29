package staffs.leavemanagement.application.leaveBalance;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import staffs.leavemanagement.application.leaveBalance.DTO.LeaveBalanceDTO;
import staffs.leavemanagement.infrastructure.leaveBalance.LeaveBalanceJpa;
import staffs.leavemanagement.infrastructure.leaveBalance.LeaveBalanceRepository;

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

package staffs.staffleave.application.leaveBalance;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import staffs.common.domain.Identity;
import staffs.common.domain.UniqueIDFactory;
import staffs.staffleave.domain.leaveBalance.LeaveBalance;
import staffs.staffleave.domain.leaveBalance.LeaveBalanceDomainException;
import staffs.staffleave.infrastructure.leaveBalance.LeaveBalanceRepository;
import staffs.staffleave.ui.LeaveBalance.AddNewLeaveBalanceCommand;

@Service
@RequiredArgsConstructor
public class LeaveBalanceApplicationService {

    private final LeaveBalanceRepository leaveBalanceRepository;
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Transactional
    public void createLeaveBalance(AddNewLeaveBalanceCommand command)
            throws LeaveBalanceDomainException {

        try {
            Identity idOfNewLeaveBalance = UniqueIDFactory.createID();
            LOG.info("New leave balance ID is {}", idOfNewLeaveBalance);

            LeaveBalance newBalance = new LeaveBalance(
                    idOfNewLeaveBalance,
                    command.getStaffId(),
                    command.getLeaveYear(),
                    command.getBalance()
            );

            leaveBalanceRepository.save(LeaveBalanceMapper.toJpa(newBalance));
            LOG.info("Leave balance created with ID: {}", idOfNewLeaveBalance);

        } catch (IllegalArgumentException e) {
            LOG.error("Error creating leave balance: {}", e.getMessage());
            throw new LeaveBalanceDomainException(e.getMessage());
        }
    }
}

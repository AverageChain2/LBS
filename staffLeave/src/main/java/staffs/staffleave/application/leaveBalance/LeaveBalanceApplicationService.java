package staffs.staffleave.application.leaveBalance;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import staffs.common.domain.Identity;
import staffs.common.domain.UniqueIDFactory;
import staffs.staffleave.application.LocalDomainEventManager;
import staffs.staffleave.domain.events.LeaveStatusChangeEvent;
import staffs.staffleave.domain.leaveBalance.LeaveBalance;
import staffs.staffleave.domain.leaveBalance.LeaveBalanceDomainException;
import staffs.staffleave.domain.leaveRequest.LeaveStatus;
import staffs.staffleave.infrastructure.leaveBalance.LeaveBalanceRepository;
import staffs.staffleave.ui.LeaveBalance.AddNewLeaveBalanceCommand;

@Service
@RequiredArgsConstructor
public class LeaveBalanceApplicationService {

    private final LeaveBalanceRepository leaveBalanceRepository;
    private final Logger LOG = LoggerFactory.getLogger(getClass());
    private final LocalDomainEventManager localDomainEventManager;
    private final LeaveStatusChangeDomainEventMapper leaveStatusChangeDomainEventMapper;

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

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @org.springframework.transaction.annotation.Transactional(propagation = Propagation.REQUIRES_NEW)
    public void handleStatusChange(LeaveStatusChangeEvent event) {
        LeaveBalance leaveBalance = leaveStatusChangeDomainEventMapper.getLeaveBalanceFromStatusChangeEvent(event);
        LeaveStatus oldStatus = event.getOldStatus();
        LeaveStatus newStatus = event.getStatus();

        // If going from neg to pos
        if ((oldStatus == LeaveStatus.Pending || oldStatus == LeaveStatus.Approved)
                && (newStatus == LeaveStatus.Cancelled || newStatus == LeaveStatus.Rejected)) {
            // Leave is being returned
            leaveBalance.updateBalance(leaveBalance.balance() + event.getLeaveAmount());
            LOG.info("Leave balance increased by {} for staff {}, new balance: {}", event.getLeaveAmount(), event.getStaffID(), leaveBalance.balance());
        } else if ((oldStatus == LeaveStatus.Cancelled || oldStatus == LeaveStatus.Rejected)
                && (newStatus == LeaveStatus.Approved)) {
            // if going from cancelled to approved
            LOG.info("This one old status{} new status{}", oldStatus, newStatus);

            leaveBalance.updateBalance(leaveBalance.balance() - event.getLeaveAmount());
            LOG.info("Leave balance decreased by {} for staff {}, new balance: {}", event.getLeaveAmount(), event.getStaffID(), leaveBalance.balance());
        }
        //For initial request creation
        else if (oldStatus == LeaveStatus.Pending && newStatus == LeaveStatus.Pending) {
            leaveBalance.updateBalance(leaveBalance.balance() - event.getLeaveAmount());
            LOG.info("This one old status{} new status{}", event.getOldStatus(), event.getStatus());

            LOG.info("Leave balance adjusted by {} for staff {}, new balance: {}", -event.getLeaveAmount(), event.getStaffID(), leaveBalance.balance());
        }


        leaveBalanceRepository.save(LeaveBalanceMapper.toJpa(leaveBalance));
        localDomainEventManager.manageDomainEvents(this, leaveBalance.listOfDomainEvents());
    }





}

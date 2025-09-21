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
import staffs.staffleave.application.LeaveRejectedOrCancelledDomainEventMapper;
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
    private final LeaveRejectedOrCancelledDomainEventMapper leaveRejectedOrCancelledDomainEventMapper;
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

    //Execute AFTER transaction that called this is committed
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)          //default phase
    @org.springframework.transaction.annotation.Transactional(propagation = Propagation.REQUIRES_NEW)                      //creates new transaction
    public void handleStatusChange(LeaveStatusChangeEvent event){                          //Listen for OrderStartedDomainEvent
        LeaveBalance leaveBalance = leaveStatusChangeDomainEventMapper.getLeaveBalanceFromStatusChangeEvent(event);
        LOG.info("Leave balance updated with ID: {}", leaveBalance.id());


//        LeaveBalance balance = LeaveRequestMapper.toDomain(existingJpa);
        if (event.getStatus() != LeaveStatus.Pending) {
            leaveBalance.updateBalance(
                    leaveBalance.balance() + event.getLeaveAmount()
            );
        } else {
            leaveBalance.updateBalance(
                    leaveBalance.balance() - event.getLeaveAmount()
            );
        }

        leaveBalanceRepository.save(LeaveBalanceMapper.toJpa(leaveBalance));

        LOG.info("Leave balance updated with ID: {}", leaveBalance.id());


        leaveBalanceRepository.save(LeaveBalanceMapper.toJpa(leaveBalance));
        //notify any subscribers + save to event store
        localDomainEventManager.manageDomainEvents(this, leaveBalance.listOfDomainEvents());
    }


}

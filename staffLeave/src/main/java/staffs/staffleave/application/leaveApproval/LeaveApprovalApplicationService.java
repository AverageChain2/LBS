package staffs.staffleave.application.leaveApproval;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import staffs.common.domain.Identity;
import staffs.common.domain.UniqueIDFactory;
import staffs.staffleave.domain.leaveApproval.LeaveApproval;
import staffs.staffleave.domain.leaveApproval.LeaveApprovalDomainException;
import staffs.staffleave.domain.leaveRequest.LeaveRequestDomainException;
import staffs.staffleave.infrastructure.eventStore.LocalDomainEventManager;
import staffs.staffleave.infrastructure.leaveApproval.LeaveApprovalJpa;
import staffs.staffleave.infrastructure.leaveApproval.LeaveApprovalRepository;
import staffs.staffleave.infrastructure.leaveRequest.LeaveRequestJpa;
import staffs.staffleave.ui.leaveApproval.AddNewLeaveApprovalCommand;
import staffs.staffleave.ui.leaveApproval.UpdateLeaveStatusCommand;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class LeaveApprovalApplicationService {

    private final LeaveApprovalRepository leaveApprovalRepository;
    private final Logger LOG = LoggerFactory.getLogger(getClass());
    private LocalDomainEventManager localDomainEventManager;

    @Transactional
    public void createLeaveApproval(AddNewLeaveApprovalCommand command)
            throws LeaveApprovalDomainException {

        try {
            Identity idOfNewLeaveApproval = UniqueIDFactory.createID();
            LOG.info("New leave approval id is {}", idOfNewLeaveApproval);
            //Pass info to aggregate to validate (including other fields from command required by aggregate)
            LeaveApproval newApproval = new LeaveApproval(
                    idOfNewLeaveApproval,
                    command.getLeaveId(),
                    command.getApproverId(),
                    command.getStatus(),
                    command.getReason(),
                    command.getApprovedAt()
            );

            leaveApprovalRepository.save(LeaveApprovalMapper.toJpa(newApproval));
            LOG.info("Leave approval created with ID: {}", idOfNewLeaveApproval);
            //Notify any subscribers
            localDomainEventManager.manageDomainEvents(this, newApproval.listOfDomainEvents());


        } catch (IllegalArgumentException e) {
            LOG.error("Error creating leave approval: {}", e.getMessage());
            throw new LeaveApprovalDomainException(e.getMessage());
        }
    }

//    public void deleteLeaveApproval(String id) throws LeaveApprovalDomainException {
//        LeaveApproval approval = findApprovalAndConvertToDomain(id);
//        leaveApprovalRepository.deleteById(approval.getId());
//        LOG.info("Leave approval deleted with ID: {}", id);
//    }

//    public void approveLeave(String id) throws LeaveApprovalDomainException {
//        LeaveApproval approval = leaveApprovalRepository.findById(id)
//    }

//    public void updateStatus(UpdateLeaveStatusCommand command) throws LeaveApprovalDomainException {
//        try {
//            //find approval id from leave id
//            Optional<LeaveApprovalJpa> leaveApprovalJpa = leaveApprovalRepository.findByLeaveRequest(command.getLeaveRequestID());
//            if (leaveApprovalJpa.isEmpty()) {
//                throw new LeaveApprovalDomainException("LeaveRequest id does not exist");
//            }
//
//
//            LeaveApprovalJpa existingJpa = leaveApprovalRepository.findById(leaveApprovalJpa.get().getId())
//                    .orElseThrow(() -> new LeaveApprovalDomainException("Leave approval not found: " + leaveApprovalJpa.get().getId()));
//
//            LeaveApproval approval = LeaveApprovalMapper.toDomain(existingJpa);
//
//            approval.updateStatus(
//                    command.getStatus(),
//                    command.getApproverId(),
//                    command.getReason(),
//                    new Date()
//            );
//
//            leaveApprovalRepository.save(LeaveApprovalMapper.toJpa(approval));
//
//            LOG.info("Leave approval updated with ID: {}", leaveApprovalJpa.get().getId());
//
//            localDomainEventManager.manageDomainEvents(this, approval.listOfDomainEvents());
//
//        } catch (IllegalArgumentException e) {
//            LOG.error("Error updating leave approval: {}", e.getMessage());
//            throw new LeaveApprovalDomainException(e.getMessage());
//        }
//    }



//    private LeaveRequest findLeaveRequestAndConvertToDomain(String id) throws LeaveApprovalDomainException {
//        Optional<LeaveApprovalJpa> approvalJpa = leaveReq.findById(id);
//        if (approvalJpa.isEmpty()) {
//            throw new LeaveApprovalDomainException("Leave approval ID not found: " + id);
//        }
//        return LeaveApprovalMapper.toDomain(approvalJpa.get());
//    }
}

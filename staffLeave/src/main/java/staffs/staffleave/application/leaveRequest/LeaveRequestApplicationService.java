package staffs.staffleave.application.leaveRequest;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import staffs.common.domain.Identity;
import staffs.common.domain.UniqueIDFactory;
import staffs.staffleave.domain.leaveApproval.LeaveStatus;
import staffs.staffleave.domain.leaveRequest.LeaveRequest;
import staffs.staffleave.domain.leaveRequest.LeaveRequestDomainException;
import staffs.staffleave.infrastructure.eventStore.LocalDomainEventManager;
import staffs.staffleave.infrastructure.leaveRequest.LeaveRequestJpa;
import staffs.staffleave.infrastructure.leaveRequest.LeaveRequestRepository;
import staffs.staffleave.infrastructure.user.UserJpa;
import staffs.staffleave.infrastructure.user.UserRepository;
import staffs.staffleave.ui.LeaveRequest.AddNewLeaveRequestCommand;
import staffs.staffleave.ui.leaveApproval.UpdateLeaveStatusCommand;

@Service
@RequiredArgsConstructor
public class LeaveRequestApplicationService {

    private final LeaveRequestRepository leaveRequestRepository;
    private final UserRepository userRepository; // Add this

    private final LocalDomainEventManager localDomainEventManager;


    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Transactional
    public void createLeaveRequest(AddNewLeaveRequestCommand command)
            throws LeaveRequestDomainException {

        try {

            UserJpa staff = userRepository.findById(command.getStaffId())
                    .orElseThrow(() -> new LeaveRequestDomainException("User not found"));

            Identity idOfNewLeaveRequest = UniqueIDFactory.createID();
            LOG.info("New leave request id is {}", idOfNewLeaveRequest);

            LeaveRequest newRequest = new LeaveRequest(
                    idOfNewLeaveRequest,
                    staff,
                    command.getStartDate(),
                    command.getEndDate(),
                    command.getLeaveAmount(),
                    LeaveStatus.Pending,
                    null
            );

            leaveRequestRepository.save(LeaveRequestMapper.toJpa(newRequest));
            LOG.info("Leave request created with ID: {}", idOfNewLeaveRequest);

        } catch (IllegalArgumentException e) {
            LOG.error("Error creating leave request: {}", e.getMessage());
            throw new LeaveRequestDomainException(e.getMessage());
        }
    }

    @Transactional
    public void updateStatus(UpdateLeaveStatusCommand command) throws LeaveRequestDomainException {
        try {

            LeaveRequestJpa existingJpa = leaveRequestRepository.findById(command.getLeaveRequestID())
                    .orElseThrow(() -> new LeaveRequestDomainException("Leave request not found: " + command.getLeaveRequestID()));

            LeaveRequest approval = LeaveRequestMapper.toDomain(existingJpa);

            approval.updateStatus(
                    command.getStatus(),
                    command.getReason()
            );

            leaveRequestRepository.save(LeaveRequestMapper.toJpa(approval));

            LOG.info("Leave approval updated with ID: {}", command.getLeaveRequestID());

            localDomainEventManager.manageDomainEvents(this, approval.listOfDomainEvents());

        } catch (IllegalArgumentException e) {
            LOG.error("Error updating leave status: {}", e.getMessage());
            throw new LeaveRequestDomainException(e.getMessage());
        }
    }
}



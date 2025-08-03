package staffs.staffleave.application.leaveRequest;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import staffs.common.domain.Identity;
import staffs.common.domain.UniqueIDFactory;
import staffs.staffleave.domain.leaveRequest.LeaveRequest;
import staffs.staffleave.domain.leaveRequest.LeaveRequestDomainException;
import staffs.staffleave.infrastructure.leaveRequest.LeaveRequestRepository;
import staffs.staffleave.ui.LeaveRequest.AddNewLeaveRequestCommand;

@Service
@RequiredArgsConstructor
public class LeaveRequestApplicationService {

    private final LeaveRequestRepository leaveRequestRepository;
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Transactional
    public void createLeaveRequest(AddNewLeaveRequestCommand command)
            throws LeaveRequestDomainException {

        try {
            Identity idOfNewLeaveRequest = UniqueIDFactory.createID();
            LOG.info("New leave request id is {}", idOfNewLeaveRequest);
            //Pass info to aggregate to validate (including other fields from command required by aggregate)
            LeaveRequest newRequest = new LeaveRequest(
                    idOfNewLeaveRequest,
                    command.getStaffId(),
                    command.getStartDate(),
                    command.getEndDate(),
                    command.getLeaveAmount()
            );

            leaveRequestRepository.save(LeaveRequestMapper.toJpa(newRequest));
            LOG.info("Leave request created with ID: {}", idOfNewLeaveRequest);

        } catch (IllegalArgumentException e) {
            LOG.error("Error creating leave request: {}", e.getMessage());
            throw new LeaveRequestDomainException(e.getMessage());
        }
    }
}

package staffs.leavemanagement.application.leaveApproval;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import staffs.common.domain.Identity;
import staffs.common.domain.UniqueIDFactory;
import staffs.leavemanagement.domain.leaveApproval.LeaveApproval;
import staffs.leavemanagement.domain.leaveApproval.LeaveApprovalDomainException;
import staffs.leavemanagement.infrastructure.leaveApproval.LeaveApprovalJpa;
import staffs.leavemanagement.infrastructure.leaveApproval.LeaveApprovalRepository;
import staffs.leavemanagement.ui.leaveApproval.AddNewLeaveApprovalCommand;

import java.util.Optional;
import staffs.leavemanagement.domain.leaveApproval.LeaveApprovalDomainException;
import staffs.leavemanagement.domain.leaveApproval.LeaveApproval;

@Service
@RequiredArgsConstructor
public class LeaveApprovalApplicationService {

    private final LeaveApprovalRepository leaveApprovalRepository;
    private final Logger LOG = LoggerFactory.getLogger(getClass());

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

//    private LeaveApproval findApprovalAndConvertToDomain(String id) throws LeaveApprovalDomainException {
//        Optional<LeaveApprovalJpa> approvalJpa = leaveApprovalRepository.findById(id);
//        if (approvalJpa.isEmpty()) {
//            throw new LeaveApprovalDomainException("Leave approval ID not found: " + id);
//        }
//        return LeaveApprovalMapper.toDomain(approvalJpa.get());
//    }
}

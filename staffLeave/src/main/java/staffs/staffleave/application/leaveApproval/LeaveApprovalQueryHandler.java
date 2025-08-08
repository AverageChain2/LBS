package staffs.staffleave.application.leaveApproval;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import staffs.staffleave.infrastructure.leaveApproval.LeaveApprovalJpa;
import staffs.staffleave.infrastructure.leaveApproval.LeaveApprovalRepository;
import staffs.staffleave.application.leaveApproval.DTO.LeaveApprovalDTO;

import java.util.Optional;

@AllArgsConstructor
@Service
public class LeaveApprovalQueryHandler {
    private LeaveApprovalRepository leaveApprovalRepository;

    //Not recommended to have this method as we could have a VERY big response
    public Iterable<LeaveApprovalJpa> findAllLeaveApprovals(){

        return leaveApprovalRepository.findAll();
    }

    public Optional<LeaveApprovalDTO> findLeaveApprovalById(String leaveApproval_id) {
        return leaveApprovalRepository.findById(leaveApproval_id)
                .map(LeaveApprovalMapper::toLeaveApprovalDTO);
    }

}

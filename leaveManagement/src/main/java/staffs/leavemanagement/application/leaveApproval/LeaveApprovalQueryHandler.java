package staffs.leavemanagement.application.leaveApproval;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import staffs.leavemanagement.application.leaveApproval.DTO.LeaveApprovalDTO;
import staffs.leavemanagement.infrastructure.leaveApproval.LeaveApprovalJpa;
import staffs.leavemanagement.infrastructure.leaveApproval.LeaveApprovalRepository;

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

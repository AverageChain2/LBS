package staffs.staffleave.application.leaveRequest;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import staffs.staffleave.application.leaveRequest.DTO.LeaveRequestDTO;
import staffs.staffleave.application.leaveRequest.LeaveRequestMapper;
import staffs.staffleave.infrastructure.leaveBalance.LeaveBalanceJpa;
import staffs.staffleave.infrastructure.leaveRequest.LeaveRequestJpa;
import staffs.staffleave.infrastructure.leaveRequest.LeaveRequestRepository;

import java.util.Optional;

@AllArgsConstructor
@Service
public class LeaveRequestQueryHandler {
    private LeaveRequestRepository leaveRequestRepository;

    //Not recommended to have this method as we could have a VERY big response
    public Iterable<LeaveRequestJpa> findAllLeaveRequests(){

        return leaveRequestRepository.findAll();
    }

    public Optional<LeaveRequestDTO> findLeaveRequestById(String leaveRequest_id) {
        return leaveRequestRepository.findById(leaveRequest_id)
                .map(LeaveRequestMapper::toLeaveRequestDTO);
    }

    public Iterable<LeaveRequestJpa> findLeaveRequestsByStaffId(String staff_id) {
        return  leaveRequestRepository.findByStaffId(staff_id);
    }

    public Iterable<LeaveRequestJpa> findLeaveBalancesByTeam(String team) {
        return  leaveRequestRepository.findByTeam(team);
    }

}

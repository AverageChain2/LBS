package staffs.staffleave.application.leaveRequest;


import staffs.staffleave.application.leaveRequest.DTO.LeaveRequestDTO;
import staffs.staffleave.infrastructure.leaveRequest.LeaveRequestJpa;

public class LeaveRequestMapper {

    public static LeaveRequestDTO toLeaveRequestDTO(LeaveRequestJpa leaveRequest) {
        return new LeaveRequestDTO(
                leaveRequest.getId(),
                leaveRequest.getStaffId(),
                leaveRequest.getStatus(),
                leaveRequest.getStartDate(),
                leaveRequest.getEndDate(),
                leaveRequest.getLeaveAmount()
        );
    }
}

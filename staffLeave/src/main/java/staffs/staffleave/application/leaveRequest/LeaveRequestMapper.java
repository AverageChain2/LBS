package staffs.staffleave.application.leaveRequest;


import staffs.common.domain.Identity;
import staffs.staffleave.application.leaveRequest.DTO.LeaveRequestDTO;
import staffs.staffleave.domain.leaveRequest.LeaveRequest;
import staffs.staffleave.infrastructure.leaveRequest.LeaveRequestJpa;

public class LeaveRequestMapper {

    public static LeaveRequestDTO toLeaveRequestDTO(LeaveRequestJpa leaveRequest) {
        return new LeaveRequestDTO(
                leaveRequest.getId(),
                leaveRequest.getStaffId(),
                leaveRequest.getStartDate(),
                leaveRequest.getEndDate(),
                leaveRequest.getLeaveAmount()
        );
    }

    // Domain to JPA
    public static LeaveRequestJpa toJpa(LeaveRequest leaveRequest) {
        LeaveRequestJpa jpa = new LeaveRequestJpa();
        jpa.setId(leaveRequest.id().id());
        jpa.setStaffId(leaveRequest.staffID());
        jpa.setStartDate(leaveRequest.startDate());
        jpa.setEndDate(leaveRequest.endDate());
        jpa.setLeaveAmount(leaveRequest.leaveAmount());
        return jpa;
    }

    // JPA to Domain
    public static LeaveRequest toDomain(LeaveRequestJpa jpa) {
        return new LeaveRequest(
                new Identity(jpa.getId()),
                jpa.getStaffId(),
                jpa.getStartDate(),
                jpa.getEndDate(),
                jpa.getLeaveAmount()
        );
    }
}

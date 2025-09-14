package staffs.staffleave.application.leaveRequest;


import staffs.common.domain.Identity;
import staffs.staffleave.application.leaveRequest.DTO.LeaveRequestDTO;
import staffs.staffleave.domain.leaveRequest.LeaveRequest;
import staffs.staffleave.infrastructure.leaveRequest.LeaveRequestJpa;

public class LeaveRequestMapper {





        public static LeaveRequestDTO toLeaveRequestDTO(LeaveRequestJpa leaveRequest) {
            return new LeaveRequestDTO(
                    leaveRequest.getId(),
                    leaveRequest.getStaffID().getId(),
                    leaveRequest.getStartDate(),
                    leaveRequest.getEndDate(),
                    leaveRequest.getLeaveAmount(),
                    leaveRequest.getStatus().toString(),
                    leaveRequest.getReason()
            );
        }



    // Domain to JPA


    public static LeaveRequestJpa toJpa(LeaveRequest leaveRequest) {
//        UserJpa staff = userRepository.findById(leaveRequest.staffID().getId())
//                .orElseThrow(() -> new RuntimeException("User not found"));

        return LeaveRequestJpa.leaveRequestJpaOf(
                leaveRequest.id().id(),
                leaveRequest.staffID(),
                leaveRequest.startDate(),
                leaveRequest.endDate(),
                leaveRequest.leaveAmount(),
                leaveRequest.status(),
                leaveRequest.reason()
        );
    }



    // JPA to Domain
    public static LeaveRequest toDomain(LeaveRequestJpa jpa) {
        return new LeaveRequest(
                new Identity(jpa.getId()),
                jpa.getStaffID(),
                jpa.getStartDate(),
                jpa.getEndDate(),
                jpa.getLeaveAmount(),
                jpa.getStatus(),
                jpa.getReason()
        );
    }
}

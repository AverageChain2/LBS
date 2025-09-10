package staffs.staffleave.application.leaveRequest;


import staffs.common.domain.Identity;
import staffs.staffleave.application.leaveRequest.DTO.LeaveRequestDTO;
import staffs.staffleave.domain.leaveRequest.LeaveRequest;
import staffs.staffleave.infrastructure.leaveRequest.LeaveRequestJpa;
import staffs.staffleave.infrastructure.user.UserJpa;
import staffs.staffleave.infrastructure.user.UserRepository;

public class LeaveRequestMapper {





        public static LeaveRequestDTO toLeaveRequestDTO(LeaveRequestJpa leaveRequest) {
            return new LeaveRequestDTO(
                    leaveRequest.getId(),
                    leaveRequest.getStaffID().getId(), // assuming getId() returns String
                    leaveRequest.getStartDate(),
                    leaveRequest.getEndDate(),
                    leaveRequest.getLeaveAmount()
            );
        }



    // Domain to JPA


    public static LeaveRequestJpa toJpa(LeaveRequest leaveRequest, UserRepository userRepository) {
        UserJpa staff = userRepository.findById(leaveRequest.staffID().getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        return LeaveRequestJpa.leaveRequestJpaOf(
                leaveRequest.id().id(),
                staff,
                leaveRequest.startDate(),
                leaveRequest.endDate(),
                leaveRequest.leaveAmount()
        );
    }



    // JPA to Domain
    public static LeaveRequest toDomain(LeaveRequestJpa jpa) {
        return new LeaveRequest(
                new Identity(jpa.getId()),
                jpa.getStaffID(),
                jpa.getStartDate(),
                jpa.getEndDate(),
                jpa.getLeaveAmount()
        );
    }
}

package staffs.leavemanagement.application.leaveApproval;


import staffs.common.domain.Identity;
import staffs.leavemanagement.application.leaveApproval.DTO.LeaveApprovalDTO;
import staffs.leavemanagement.domain.leaveApproval.LeaveApproval;
import staffs.leavemanagement.infrastructure.leaveApproval.LeaveApprovalJpa;

public class LeaveApprovalMapper {

    public static LeaveApprovalDTO toLeaveApprovalDTO(LeaveApprovalJpa leaveApproval) {
        return new LeaveApprovalDTO(
                leaveApproval.getId(),
                leaveApproval.getLeaveId(),
                leaveApproval.getApproverId(),
                leaveApproval.getStatus(),
                leaveApproval.getReason(),
                leaveApproval.getApprovedAt()

        );
    }

    // Domain to JPA
    public static LeaveApprovalJpa toJpa(LeaveApproval leaveApproval) {
        LeaveApprovalJpa jpa = new LeaveApprovalJpa();
        jpa.setId(leaveApproval.id().id());
        jpa.setLeaveId(leaveApproval.leaveID());
        jpa.setApproverId(leaveApproval.approverID());
        jpa.setStatus(leaveApproval.status());
        jpa.setReason(leaveApproval.reason());
        jpa.setApprovedAt(leaveApproval.approvedAt());
        return jpa;
    }

    // JPA to Domain
    public static LeaveApproval toDomain(LeaveApprovalJpa jpa) {
        return new LeaveApproval(
                new Identity(jpa.getId()),
                jpa.getLeaveId(),
                jpa.getApproverId(),
                jpa.getStatus(),
                jpa.getReason(),
                jpa.getApprovedAt()
        );
    }
}

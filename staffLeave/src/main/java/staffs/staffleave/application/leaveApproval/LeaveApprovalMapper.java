package staffs.staffleave.application.leaveApproval;


import staffs.common.domain.Identity;

import staffs.staffleave.application.leaveApproval.DTO.LeaveApprovalDTO;
import staffs.staffleave.domain.leaveApproval.LeaveApproval;
import staffs.staffleave.infrastructure.leaveApproval.LeaveApprovalJpa;

public class LeaveApprovalMapper {

    public static LeaveApprovalDTO toLeaveApprovalDTO(LeaveApprovalJpa leaveApproval) {
        return new LeaveApprovalDTO(
                leaveApproval.getId(),
                leaveApproval.getLeaveRequest(),
                leaveApproval.getApproverId(),
                leaveApproval.getStatus(),
                leaveApproval.getReason(),
                leaveApproval.getApprovedAt()
        );
    }

    public static LeaveApprovalJpa toJpa(LeaveApproval leaveApproval) {
        LeaveApprovalJpa jpa = new LeaveApprovalJpa();
        jpa.setId(leaveApproval.id().id());
        jpa.setLeaveRequest(leaveApproval.leaveID()); // ✅ Added
        jpa.setApproverId(leaveApproval.approverID());
        jpa.setStatus(leaveApproval.status());
        jpa.setReason(leaveApproval.reason());
        jpa.setApprovedAt(leaveApproval.approvedAt());
        return jpa;
    }

    public static LeaveApproval toDomain(LeaveApprovalJpa jpa) {
        return new LeaveApproval(
                new Identity(jpa.getId()),
                jpa.getLeaveRequest(),
                jpa.getApproverId(),
                jpa.getStatus(),
                jpa.getReason(),
                jpa.getApprovedAt()
        );
    }
}


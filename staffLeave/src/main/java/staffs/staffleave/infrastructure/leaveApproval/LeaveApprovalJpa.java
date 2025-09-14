package staffs.staffleave.infrastructure.leaveApproval;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import staffs.staffleave.domain.leaveApproval.LeaveStatus;
import staffs.staffleave.infrastructure.leaveRequest.LeaveRequestJpa;
import staffs.staffleave.infrastructure.user.UserJpa;

import java.util.Date;

@Entity(name = "leaveApproval")
@Table(name = "leave_approval")
@ToString
@Getter
@Setter
public class LeaveApprovalJpa {

    @Id
    @Column(name = "id")
    private String id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "leave_id", referencedColumnName = "id")
    private LeaveRequestJpa leaveRequest;

    @ManyToOne
    @JoinColumn(name = "approver_id", referencedColumnName = "id")
    private UserJpa approverId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private LeaveStatus status;

    @Column(name = "reason")
    private String reason;

    @Column(name = "approved_at")
    private Date approvedAt;

    public LeaveApprovalJpa() {}

    protected LeaveApprovalJpa(String id, LeaveRequestJpa leaveRequest, UserJpa approverId, LeaveStatus status, String reason, Date approvedAt) {
        this.id = id;
        this.leaveRequest = leaveRequest;
        this.approverId = approverId;
        this.status = status;
        this.reason = reason;
        this.approvedAt = approvedAt;
    }

    public static LeaveApprovalJpa of(String id, LeaveRequestJpa leaveRequest, UserJpa approverId, LeaveStatus status, String reason, Date approvedAt) {
        return new LeaveApprovalJpa(id, leaveRequest, approverId, status, reason, approvedAt);
    }
}

package staffs.leavemanagement.infrastructure.leaveApproval;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity(name = "leaveApproval")
@Table(name = "leaveApproval")
@ToString
@Getter
@Setter
public class LeaveApprovalJpa {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "leave_id")
    private String leaveId;

    @Column(name = "approver_id")
    private String approverId;

    @Column(name = "status")
    private String status;

    @Column(name = "reason")
    private String reason;

    @Column(name = "approved_at")
    private Date approvedAt;

    public LeaveApprovalJpa() {
    }

    protected LeaveApprovalJpa(String id, String leaveId, String approverId, String status, String reason, Date approvedAt) {
        this.id = id;
        this.leaveId = leaveId;
        this.approverId = approverId;
        this.status = status;
        this.reason = reason;
        this.approvedAt = approvedAt;
    }

    public static LeaveApprovalJpa of(String id, String leaveId, String approverId, String status, String reason, Date approvedAt) {
        return new LeaveApprovalJpa(id, leaveId, approverId, status, reason, approvedAt);
    }

}

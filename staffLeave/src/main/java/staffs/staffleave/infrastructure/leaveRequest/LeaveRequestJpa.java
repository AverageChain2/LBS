package staffs.staffleave.infrastructure.leaveRequest;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import staffs.staffleave.domain.leaveApproval.LeaveStatus;
import staffs.staffleave.infrastructure.user.UserJpa;

import java.util.Date;

@Entity(name = "leaveRequest")
@Table(name = "leave_request")
@ToString
@Getter
@Setter
public class LeaveRequestJpa {

    @Id
    @Column(name = "id")
    private String id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "staff_id")
    private UserJpa staffID;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "leave_amount")
    private Float leaveAmount;


    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private LeaveStatus status;

    @Column(name = "reason")
    private String reason;

    public LeaveRequestJpa() {
        // Default constructor for JPA
    }

    // Custom constructor
    protected LeaveRequestJpa(String id, UserJpa staffID, Date startDate, Date endDate, Float leaveAmount, LeaveStatus status, String reason) {
        this.id = id;
        this.staffID = staffID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.leaveAmount = leaveAmount;
    }

    // Factory method
    public static LeaveRequestJpa leaveRequestJpaOf(String id, UserJpa staffId, Date startDate, Date endDate, Float leaveAmount,  LeaveStatus status, String reason) {
        return new LeaveRequestJpa(id, staffId, startDate, endDate, leaveAmount,  status, reason);
    }
}

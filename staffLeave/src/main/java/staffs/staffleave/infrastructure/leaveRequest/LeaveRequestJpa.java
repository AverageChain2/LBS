package staffs.staffleave.infrastructure.leaveRequest;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import staffs.staffleave.infrastructure.user.UserJpa;

import java.util.Date;

@Entity(name = "leaveRequest")
@Table(name = "leaveRequest")
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

    public LeaveRequestJpa() {
        // Default constructor for JPA
    }

    // Custom constructor
    protected LeaveRequestJpa(String id, UserJpa staffID, Date startDate, Date endDate, Float leaveAmount) {
        this.id = id;
        this.staffID = staffID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.leaveAmount = leaveAmount;
    }

    // Factory method
    public static LeaveRequestJpa leaveRequestJpaOf(String id, UserJpa staffId, Date startDate, Date endDate, Float leaveAmount) {
        return new LeaveRequestJpa(id, staffId, startDate, endDate, leaveAmount);
    }
}

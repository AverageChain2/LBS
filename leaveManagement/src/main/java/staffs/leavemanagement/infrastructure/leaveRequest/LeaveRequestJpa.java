package staffs.leavemanagement.infrastructure.leaveRequest;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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

    @Column(name = "staff_id")
    private String staffId;

    @Column(name = "status")
    private String status;

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
    protected LeaveRequestJpa(String id, String staffId, String status, Date startDate, Date endDate, Float leaveAmount) {
        this.id = id;
        this.staffId = staffId;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
        this.leaveAmount = leaveAmount;
    }

    // Factory method
    public static LeaveRequestJpa leaveRequestJpaOf(String id, String staffId, String status, Date startDate, Date endDate, Float leaveAmount) {
        return new LeaveRequestJpa(id, staffId, status, startDate, endDate, leaveAmount);
    }
}

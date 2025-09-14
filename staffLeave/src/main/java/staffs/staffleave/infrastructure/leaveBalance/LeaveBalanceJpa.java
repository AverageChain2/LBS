package staffs.staffleave.infrastructure.leaveBalance;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "leaveBalance")
@Table(name = "leave_balance")
@ToString
@Getter
@Setter
public class LeaveBalanceJpa {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "staff_id")
    private String staffId;

    @Column(name = "leaveYear")
    private String leaveYear;

    @Column(name = "balance")
    private Float balance;

    public LeaveBalanceJpa() {
        // Default constructor for JPA
    }

    // Custom constructor
    protected LeaveBalanceJpa(String id, String staffId, String leaveYear, Float balance) {
        this.id = id;
        this.staffId = staffId;
        this.leaveYear = leaveYear;
        this.balance = balance;
    }

    // Factory method
    public static LeaveBalanceJpa leaveBalanceJpaOf(String id, String staffId, String leaveYear, Float balance) {
        return new LeaveBalanceJpa(id, staffId, leaveYear, balance);
    }
}

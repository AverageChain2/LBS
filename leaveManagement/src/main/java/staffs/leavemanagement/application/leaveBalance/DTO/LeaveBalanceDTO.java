package staffs.leavemanagement.application.leaveBalance.DTO;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@EqualsAndHashCode
@Getter
@ToString
public class LeaveBalanceDTO {
    private String id;
    private String staffId;
    private String year;
    private Float balance;
}

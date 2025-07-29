package staffs.leavemanagement.infrastructure.leaveBalance;

import org.springframework.data.repository.CrudRepository;
import staffs.leavemanagement.domain.leaveBalance.LeaveBalance;

public interface LeaveBalanceRepository extends CrudRepository<LeaveBalanceJpa, String> {
}

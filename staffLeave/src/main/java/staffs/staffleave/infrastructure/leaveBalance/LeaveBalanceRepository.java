package staffs.staffleave.infrastructure.leaveBalance;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface LeaveBalanceRepository extends CrudRepository<LeaveBalanceJpa, String> {
    @Query("FROM leaveBalance lb WHERE lb.staffId = :staffId ")
    Iterable<LeaveBalanceJpa> findBalancesByStaffId(String staffId);
    @Query("FROM leaveBalance lb WHERE lb.staffId = :staffId  and lb.leaveYear = :leaveYear ")
    Optional<LeaveBalanceJpa> findBalanceByStaffIdAndYear(String staffId, String leaveYear);

}

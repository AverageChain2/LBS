package staffs.staffleave.infrastructure.leaveRequest;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface LeaveRequestRepository extends CrudRepository<LeaveRequestJpa, String> {
    @Query("FROM leaveRequest lb WHERE lb.staffID.id = :staffId ")
    Iterable<LeaveRequestJpa> findByStaffId(String staffId);
    @Query("FROM leaveRequest lb WHERE lb.staffID.team = :team ")
    Iterable<LeaveRequestJpa> findByTeam(String team);
}

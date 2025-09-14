package staffs.staffleave.infrastructure.leaveApproval;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import staffs.staffleave.infrastructure.leaveRequest.LeaveRequestJpa;

import java.util.Optional;

public interface LeaveApprovalRepository extends CrudRepository<LeaveApprovalJpa, String> {
    @Query("FROM leaveApproval la WHERE la.leaveRequest.id = :leaveRequest ")
    Optional<LeaveApprovalJpa> findByLeaveRequest(String leaveRequest);


}

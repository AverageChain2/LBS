package staffs.leavemanagement.infrastructure.leaveApproval;

import org.springframework.data.repository.CrudRepository;

public interface LeaveApprovalRepository extends CrudRepository<LeaveApprovalJpa, String> {
}

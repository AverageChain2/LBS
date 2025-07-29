package staffs.leavemanagement.infrastructure.leaveRequest;

import org.springframework.data.repository.CrudRepository;
import staffs.leavemanagement.domain.leaveRequest.LeaveRequest;

public interface LeaveRequestRepository extends CrudRepository<LeaveRequestJpa, String> {
}

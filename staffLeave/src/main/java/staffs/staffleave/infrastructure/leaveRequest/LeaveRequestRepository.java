package staffs.staffleave.infrastructure.leaveRequest;

import org.springframework.data.repository.CrudRepository;

public interface LeaveRequestRepository extends CrudRepository<LeaveRequestJpa, String> {
}

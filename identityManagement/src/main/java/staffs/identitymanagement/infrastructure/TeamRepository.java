package staffs.identitymanagement.infrastructure;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import staffs.common.security.Team;


@Repository
public interface TeamRepository extends CrudRepository<Team, Long> {
}
package staffs.identitymanagement.infrastructure;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import staffs.common.security.RoleJpa;


@Repository
public interface RoleRepository extends CrudRepository<RoleJpa, Long> {

}
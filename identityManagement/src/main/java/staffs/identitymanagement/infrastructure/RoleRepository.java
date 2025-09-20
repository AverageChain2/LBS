package staffs.identitymanagement.infrastructure;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import staffs.common.security.Role;


@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

}
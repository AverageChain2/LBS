package staffs.staffleave.infrastructure.user;

import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<UserJpa, String> {
}


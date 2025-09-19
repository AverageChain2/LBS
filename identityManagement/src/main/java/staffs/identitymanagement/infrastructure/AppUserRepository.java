package staffs.identitymanagement.infrastructure;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import staffs.common.security.AppUserJpa;

import java.util.Optional;

@Repository
public interface AppUserRepository extends CrudRepository<AppUserJpa, String> {
    @Query("FROM app_user u WHERE u.userName=:username")
    Optional<AppUserJpa> findByUsername(String username);
}
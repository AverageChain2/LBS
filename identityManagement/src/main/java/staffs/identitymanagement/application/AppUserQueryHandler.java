package staffs.identitymanagement.application;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import staffs.common.security.AppUser;
import staffs.identitymanagement.application.DTO.AppUserDTO;
import staffs.identitymanagement.infrastructure.UserRepository;


import java.util.Optional;

@AllArgsConstructor
@Service
public class AppUserQueryHandler {
    private UserRepository appUserRepository;

    //Not recommended to have this method as we could have a VERY big response
    public Iterable<AppUser> findAllUsers(){

        return appUserRepository.findAll();
    }

    public Optional<AppUserDTO> findUserById(String user_id) {
        return appUserRepository.findById(user_id)
                .map(AppUserMapper::jpaToDTO);
    }

}

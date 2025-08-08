package staffs.staffleave.application.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import staffs.staffleave.application.user.DTO.UserDTO;
import staffs.staffleave.infrastructure.user.UserJpa;
import staffs.staffleave.infrastructure.user.UserRepository;


import java.util.Optional;

@AllArgsConstructor
@Service
public class UserQueryHandler {
    private UserRepository userRepository;

    //Not recommended to have this method as we could have a VERY big response
    public Iterable<UserJpa> findAllUsers(){

        return userRepository.findAll();
    }

    public Optional<UserDTO> findUserById(String user_id) {
        return userRepository.findById(user_id)
                .map(UserMapper::toUserDTO);
    }

}

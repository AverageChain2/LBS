package staffs.identitymanagement.application;



import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import staffs.common.domain.Identity;
import staffs.common.domain.UniqueIDFactory;
import staffs.common.security.Role;
import staffs.identitymanagement.domain.user.AppUserDomain;
import staffs.identitymanagement.domain.user.AppUserDomainException;
import staffs.identitymanagement.infrastructure.RoleRepository;
import staffs.identitymanagement.infrastructure.UserRepository;
import staffs.identitymanagement.ui.AddNewAppUserCommand;




@Service
@RequiredArgsConstructor
public class AppUserApplicationService {

    private final UserRepository appUserRepository;
//    private final TeamRepository teamRepository;
    private final RoleRepository roleRepository;

//    private final LeaveBalanceRepository leaveBalanceRepository;

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Transactional
    public void createAppUser(AddNewAppUserCommand command) throws AppUserDomainException {
        try {

            Role roleJpa = roleRepository.findById(command.getRoleId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid role ID"));

//            TeamJpa teamJpa = teamRepository.findById(command.getTeamId())
//                    .orElseThrow(() -> new IllegalArgumentException("Invalid team ID"));


            Identity idOfNewUser = UniqueIDFactory.createID();
            LOG.info("New user id is {}", idOfNewUser);

            AppUserDomain newAppUser = new AppUserDomain(
                    idOfNewUser,
                    command.getUsername(),
                    command.getPassword(),
                    command.getFirstname(),
                    command.getSurname(),
                    command.getEmail(),
                    roleJpa
//                    teamJpa
            );

            appUserRepository.save(AppUserMapper.domainToJpa(newAppUser));
            LOG.info("User created with ID: {}", idOfNewUser);

//            Identity idOfNewLeaveBalance = UniqueIDFactory.createID();
//            LeaveBalance balance = new LeaveBalance(
//                    idOfNewLeaveBalance,
//                    idOfNewUser.toString(),
//                    Year.now().toString(),
//                    210.00F
//            );
//            leaveBalanceRepository.save(LeaveBalanceMapper.toJpa(balance));

        } catch (IllegalArgumentException e) {
            LOG.error("Error creating User: {}", e.getMessage());
            throw new AppUserDomainException(e.getMessage());
        }
    }




//    public UserDTO getUserById(String id) {
//        return userRepository.findById(id)
//                .map(UserMapper::toDomain)
//                .map(UserMapper::toUserDTO)
//                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
//    }

    // Optional: update user
//    @Transactional
//    public void updateUser(UserDTO dto) {
//        User user = UserMapper.toDomain(dto);
//        userRepository.save(UserMapper.toJpa(user));
//        LOG.info("User updated: {}", dto.getId());
//    }
}

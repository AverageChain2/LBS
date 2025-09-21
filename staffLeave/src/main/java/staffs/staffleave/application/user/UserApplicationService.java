package staffs.staffleave.application.user;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import staffs.common.domain.Identity;
import staffs.common.domain.UniqueIDFactory;
import staffs.staffleave.application.leaveBalance.LeaveBalanceMapper;
import staffs.staffleave.application.leaveRequest.LeaveRequestMapper;
import staffs.staffleave.application.user.DTO.UserDTO;
import staffs.staffleave.domain.leaveBalance.LeaveBalance;
import staffs.staffleave.domain.leaveRequest.LeaveRequest;
import staffs.staffleave.domain.leaveRequest.LeaveRequestDomainException;
import staffs.staffleave.domain.user.User;
import staffs.staffleave.domain.user.UserDomainException;
import staffs.staffleave.infrastructure.leaveBalance.LeaveBalanceRepository;
import staffs.staffleave.infrastructure.user.UserRepository;
import staffs.staffleave.ui.user.AddNewUserCommand;

import java.time.LocalDate;
import java.time.Year;


@Service
@RequiredArgsConstructor
public class UserApplicationService {

    private final UserRepository userRepository;
    private final Logger LOG = LoggerFactory.getLogger(getClass());
    private final LeaveBalanceRepository leaveBalanceRepository;

    @Transactional
    public void createUser(AddNewUserCommand command)
         throws UserDomainException {

            try {
                Identity idOfNewUser = UniqueIDFactory.createID();
                LOG.info("New user id is {}", idOfNewUser);
                //Pass info to aggregate to validate (including other fields from command required by aggregate)
                User newUser = new User(
                        idOfNewUser,
                        command.getFullname_firstname(),
                        command.getFullname_surname(),
                        command.getRole(),
                        command.getTeam()
                );

                userRepository.save(UserMapper.toJpa(newUser));
                LOG.info("User created with ID: {}", idOfNewUser);

                //Create Leave Balance
                Identity idOfNewLeaveBalance = UniqueIDFactory.createID();
                LeaveBalance balance = new LeaveBalance(idOfNewLeaveBalance, idOfNewUser.toString(), Year.now().toString(), 210.00F);
                leaveBalanceRepository.save(LeaveBalanceMapper.toJpa(balance));

            } catch (IllegalArgumentException e) {
                LOG.error("Error creating User: {}", e.getMessage());
                throw new UserDomainException(e.getMessage());
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

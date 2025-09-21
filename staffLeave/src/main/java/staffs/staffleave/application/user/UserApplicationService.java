package staffs.staffleave.application.user;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import staffs.common.domain.Identity;
import staffs.common.domain.UniqueIDFactory;
import staffs.staffleave.application.leaveBalance.LeaveBalanceMapper;
import staffs.staffleave.application.user.events.DomainEventManager;
import staffs.staffleave.application.user.events.NewAppUserAddedMessage;
import staffs.staffleave.domain.leaveBalance.LeaveBalance;
import staffs.staffleave.domain.user.User;
import staffs.staffleave.infrastructure.leaveBalance.LeaveBalanceRepository;
import staffs.staffleave.infrastructure.user.UserJpa;
import staffs.staffleave.infrastructure.user.UserRepository;

import java.time.Year;
import java.util.Optional;


@Service
@AllArgsConstructor
public class UserApplicationService {

    private final UserRepository userRepository;
    private DomainEventManager domainEventManager;
    private final Logger LOG = LoggerFactory.getLogger(getClass());
    private final LeaveBalanceRepository leaveBalanceRepository;

    public void addNewUser(NewAppUserAddedMessage event)
    {
        //Check if restaurant is already present
        Optional<UserJpa> userJpa = userRepository.findById(event.getAggregateId());
        if (userJpa.isPresent()) {
            LOG.info("User already exists so no need to add here");
            return;
        }

        Identity id = new Identity(event.getAggregateId());
        //Create new restaurant with event
        User user = User.UserOfWithEvent(
                id,
                event.getFirstname(),
                event.getSurname(),
                event.getRole(),
                event.getTeam());

        userRepository.save(convertDomainToJpa(user));

        domainEventManager.manageDomainEvents(this, user.listOfEvents);

        LOG.info("User added successfully to staffLeave context");

        //Create Leave Balance
        Identity idOfNewLeaveBalance = UniqueIDFactory.createID();
        LeaveBalance balance = new LeaveBalance(idOfNewLeaveBalance, id.toString(), Year.now().toString(), 210.00F);
        leaveBalanceRepository.save(LeaveBalanceMapper.toJpa(balance));

//
//
//                //Create Leave Balance
//                Identity idOfNewLeaveBalance = UniqueIDFactory.createID();
//                LeaveBalance balance = new LeaveBalance(idOfNewLeaveBalance, idOfNewUser.toString(), Year.now().toString(), 210.00F);
//                leaveBalanceRepository.save(LeaveBalanceMapper.toJpa(balance));
//
//            } catch (IllegalArgumentException e) {
//                LOG.error("Error creating User: {}", e.getMessage());
//                throw new UserDomainException(e.getMessage());
//            }
        }

    private UserJpa convertDomainToJpa(User user) {
        UserJpa newAppUserJpa = new UserJpa();
        newAppUserJpa.setId(user.id().id());
        newAppUserJpa.setFirstname(user.firstname());
        newAppUserJpa.setSurname(user.surname());
        newAppUserJpa.setRole(user.role());
        newAppUserJpa.setTeam(user.team());
        return newAppUserJpa;
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

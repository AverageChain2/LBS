package staffs.staffleave.application;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import staffs.staffleave.application.leaveBalance.DTO.LeaveBalanceDTO;
import staffs.staffleave.application.leaveBalance.LeaveBalanceMapper;
import staffs.staffleave.infrastructure.eventStore.EventStoreJpa;
import staffs.staffleave.infrastructure.eventStore.EventStoreRepository;
import staffs.staffleave.infrastructure.leaveBalance.LeaveBalanceJpa;
import staffs.staffleave.infrastructure.leaveBalance.LeaveBalanceRepository;

import java.util.Optional;

@AllArgsConstructor
@Service
public class EventStoreQueryHandler {
    private EventStoreRepository eventStoreRepository;

    public Iterable<EventStoreJpa> findAllEvents(){

        return eventStoreRepository.findAll();
    }

    public Optional<EventStoreJpa> findEventById(Long eventID) {
        return eventStoreRepository.findById(eventID);
    }

}

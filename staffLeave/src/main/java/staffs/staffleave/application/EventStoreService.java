package staffs.staffleave.application;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import staffs.common.events.Event;
import staffs.staffleave.infrastructure.eventStore.EventStoreJpa;
import staffs.staffleave.infrastructure.eventStore.EventStoreRepository;

import java.time.LocalDate;

@Component
@AllArgsConstructor
public class EventStoreService {

    private final EventStoreRepository repository;
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    public void append(Event event) {
        EventStoreJpa newEvent = EventStoreJpa.create(
                LocalDate.now(),
                event.toString(),
                event.getClass().getSimpleName()
        );
        repository.save(newEvent);
        LOG.info("Added to event store " + newEvent + "\n");
    }
}

package staffs.staffleave.infrastructure.eventStore;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import staffs.common.events.Event;

import java.util.List;

@Component
@AllArgsConstructor
public class LocalDomainEventManager {

    private final ApplicationEventPublisher eventPublisher;
    private final EventStoreService eventStoreService;
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    public void manageDomainEvents(Object source, List<Event> events) {
        for (Event event : events) {
            LOG.info("{} -> {}", source.getClass().getSimpleName(), event);
            eventPublisher.publishEvent(event);
            eventStoreService.append(event);
        }
    }
}


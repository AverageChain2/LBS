package staffs.staffleave.application.user.events;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import staffs.common.events.Event;
import staffs.common.events.LocalEvent;
import staffs.common.events.RemoteEvent;
import staffs.staffleave.application.EventStoreService;

import java.util.List;
import java.util.Objects;

@Component
@AllArgsConstructor
public class DomainEventManager { //change name to DomainEventManager
    private Environment env;
    private RabbitTemplate sender;

    private ApplicationEventPublisher eventPublisher;
    private EventStoreService eventStoreService;

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    public void manageDomainEvents(Object nameOfService, List<Event> events) {
        for (Event event : events){
            LOG.info("{} -> {}", nameOfService.getClass().getSimpleName(), event);

            if (event instanceof LocalEvent) {
                eventPublisher.publishEvent(event);
            }
//            if (event instanceof RemoteEvent){
//                sendRemoteEvent(event);
//            }
            //add event to local event store
            eventStoreService.append(event);
        }
    }

}
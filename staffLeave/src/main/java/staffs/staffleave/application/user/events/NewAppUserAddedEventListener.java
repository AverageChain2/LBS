package staffs.staffleave.application.user.events;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import staffs.staffleave.application.user.UserApplicationService;

@AllArgsConstructor
@Component
public class NewAppUserAddedEventListener {
    private UserApplicationService userApplicationService;

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @RabbitListener(queues = "${rabbit.queue.new-appUser}", id = "newAppUserCreatedListener")
    public void receiveNewAppUserAdded(NewAppUserAddedMessage event) {
        LOG.info(event.toString());
        userApplicationService.addNewUser(event);
    }
}

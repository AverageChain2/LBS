package staffs.identitymanagement.domain.events.events;

import lombok.Getter;
import lombok.ToString;
import staffs.common.events.RemoteEvent;

import java.time.LocalDate;

@Getter
@ToString
public class NewAppUserAddedEvent implements RemoteEvent {
    private String occurredOn;
    private String aggregateId;
    private String appUserId;
    private String firstname;
    private String surname;
    private String role;
    private String team;

    public NewAppUserAddedEvent(String aggregateId,
                                String appUserId,
                                String firstname,
                                String surname, String role, String team) {
        this.occurredOn = LocalDate.now().toString();//Otherwise exception when converting to JSON
        this.aggregateId = aggregateId;
        this.appUserId = appUserId;
        this.firstname = firstname;
        this.surname = surname;
        this.role = role;
        this.team = team;
    }
}

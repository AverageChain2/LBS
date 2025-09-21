package staffs.staffleave.domain.events;

import staffs.common.events.LocalEvent;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

//Event created so locally we can record the restaurant being added to our event store
@Getter
@ToString
public class NewUserAddedEvent implements LocalEvent {
    private String occurredOn;
    private String userId;
    private String firstname;
    private String surname;
    private String role;
    private String team;

    public NewUserAddedEvent(String userId, String firstname, String surname, String role, String team) {
        this.occurredOn = LocalDate.now().toString();//Otherwise exception when converting to JSON
        this.userId = userId;
        this.firstname = firstname;
        this.surname = surname;
        this.role = role;
        this.team = team;
    }
}
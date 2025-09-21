package staffs.staffleave.application.user.events;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor//needed for Restaurant factory method withEvent
@JsonIgnoreProperties(ignoreUnknown = true) //ignores timestamp field added when serialising JSON before sending to MQ
public class NewAppUserAddedMessage {
    private String occurredOn;
    private String aggregateId;
    private String firstname;
    private String surname;
    private String role;
    private String team;
}
package staffs.common.events;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import staffs.common.domain.Identity;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
@Getter
@ToString
//I am not including PaymentMethod as we can keep this on file for future orders
public class OrderCancelledEvent implements RemoteEvent {
    private Identity aggregateID;
    private String occurredOn;

    @JsonCreator //Incoming message has deserialisation issues with explicit JSON mapping
    public OrderCancelledEvent(@JsonProperty("aggregateID") Identity aggregateID) {
        this.occurredOn = LocalDate.now().toString();//Otherwise exception when converting to JSON
        this.aggregateID = aggregateID;
    }
}

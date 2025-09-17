package staffs.common.events;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import staffs.common.domain.Identity;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@ToString
public class OrderConfirmedEvent implements RemoteEvent {
    private Identity aggregateId; //Kitchen aggregate
    private String orderID;
    private String occurredOn;

    @JsonCreator //Incoming message has deserialisation issues with explicit JSON mapping
    public OrderConfirmedEvent(@JsonProperty("aggregateID") Identity aggregateID,
                               @JsonProperty("orderID") String orderID) {
        this.occurredOn = LocalDate.now().toString();//Otherwise exception when converting to JSON
        this.aggregateId = aggregateID;
        this.orderID = orderID;
    }
}
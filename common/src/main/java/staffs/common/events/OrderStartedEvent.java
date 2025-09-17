package staffs.common.events;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import staffs.common.domain.FullName;
import staffs.common.domain.Identity;
import staffs.common.dto.OrderItemDTO;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Getter
@ToString
public class OrderStartedEvent implements RemoteEvent {
    private Identity aggregateID;
    private String occurredOn;

    //Buyer details
    private String buyerID;
    private FullName fullName;
    private String restaurantId;
    private String restaurantName;
    List<OrderItemDTO> orderItems;

    @JsonCreator //Incoming message has deserialisation issues with explicit JSON mapping
    public OrderStartedEvent(
            @JsonProperty("aggregateID") Identity aggregateID, //Had to modify identity to explicit mapping (see common)
            @JsonProperty("buyerID") String buyerID,
            @JsonProperty("restaurantId") String restaurantId,
            @JsonProperty("restaurantName") String restaurantName,
            @JsonProperty("fullName") FullName fullName,
            @JsonProperty("orderItems") List<OrderItemDTO> orderItems
    ) {
        this.occurredOn = LocalDate.now().toString();//Otherwise exception when converting to JSON
        this.aggregateID = aggregateID;

        this.buyerID = buyerID;
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.fullName = fullName;
        this.orderItems = orderItems;
    }
}
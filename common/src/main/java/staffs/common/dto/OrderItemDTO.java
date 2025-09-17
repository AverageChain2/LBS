package staffs.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@EqualsAndHashCode
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDTO {
    @JsonIgnore
    private String orderID; //No need to display this in reports
    private long productID;
    private String productName;
    private double unitPrice;
    private int units;
}
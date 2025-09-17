package staffs.common.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode
@Getter
@ToString
@AllArgsConstructor
public class AddressDTO {
    private final String houseNameNumber;
    private final String street;
    private final String town;
    private final String postcode;
}

package staffs.staffleave.application.user.DTO;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;


@AllArgsConstructor
@EqualsAndHashCode
@Getter
@ToString
public class UserDTO {
    private String id;
    private String fullname_firstname;
    private String fullname_surname;
    private String role;
    private String team_name;
}

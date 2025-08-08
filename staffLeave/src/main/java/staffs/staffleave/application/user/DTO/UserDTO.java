package staffs.staffleave.application.user.DTO;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import staffs.common.domain.Identity;
import staffs.staffleave.domain.user.UserRole;

@AllArgsConstructor
@EqualsAndHashCode
@Getter
@ToString
public class UserDTO {
    private String id;
    private String fullname_firstname;
    private String fullname_surname;
    private UserRole role;
    private String teamID;
}

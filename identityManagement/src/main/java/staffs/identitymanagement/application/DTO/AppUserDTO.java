package staffs.identitymanagement.application.DTO;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@EqualsAndHashCode
@Getter
@ToString
public class AppUserDTO {
    private String id;
    private String username;
    private String password;
    private String firstName;
    private String surname;
    private String email;
    private Long roleID;
//    private Long teamID;
}

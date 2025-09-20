package staffs.identitymanagement.ui;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AddNewAppUserCommand {
    private String username;
    private String password;
    private String firstname;
    private String surname;
    private String email;
    private Long roleId;
//    private Long teamId;

    @Override
    public String toString() {
        return String.format(
                "\nUsername: %s, First Name: %s, Surname: %s, Email: %s, Role ID: %d,",
                username,
                firstname,
                surname,
                email,
                roleId
//                teamId
        );
    }
}

package staffs.staffleave.ui.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import staffs.common.domain.Identity;
import staffs.staffleave.domain.user.UserRole;

@Getter
@AllArgsConstructor
public class AddNewUserCommand {
//    private Identity id;
    private String fullname_firstname;
    private String fullname_surname;
    private UserRole role;
    private String teamID;

    @Override
    public String toString() {
        return String.format(
                "\nFirst Name: %s, Surname: %s, Role: %s, Team ID: %s",
//                id,
                fullname_firstname,
                fullname_surname,
                role,
                teamID
        );
    }
}

package staffs.staffleave.ui.user;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class AddNewUserCommand {
//    private Identity id;
    private String fullname_firstname;
    private String fullname_surname;
    private String role;
    private String team;

    @Override
    public String toString() {
        return String.format(
                "\nFirst Name: %s, Surname: %s, Role: %s, Team ID: %s",
//                id,
                fullname_firstname,
                fullname_surname,
                role,
                team
        );
    }
}

package staffs.identitymanagement.domain.user;

import lombok.ToString;
import staffs.common.domain.Entity;
import staffs.common.domain.Identity;
import staffs.common.security.Role;


@ToString
public class AppUserDomain extends Entity {

    private String username;
    private String password;
    private String firstname;
    private String surname;
    private String email;
    private Role roleJpa;
//    private TeamJpa teamJpa;

    public AppUserDomain(Identity id, String username, String password, String firstname, String surname,
                         String email, Role roleJpa
//                         TeamJpa teamJpa
    ) {
        super(id);
        setUsername(username);
        setPassword(password);
        setFirstname(firstname);
        setSurname(surname);
        setEmail(email);
        this.roleJpa = roleJpa;
//        this.teamJpa = teamJpa;
    }

    public static AppUserDomain userOf(Identity id, String username, String password, String firstname,
                                       String surname, String email, Role roleJpa
//            , TeamJpa teamJpa
    ) {
        return new AppUserDomain(id, username, password, firstname, surname, email, roleJpa
//                , teamJpa
        );
    }

    private void setUsername(String username) {
        assertArgumentNotEmpty(username, "Username cannot be empty");
        this.username = username;
    }

    private void setPassword(String password) {
        assertArgumentNotEmpty(password, "Password cannot be empty");
        this.password = password;
    }

    private void setFirstname(String firstname) {
        assertArgumentNotEmpty(firstname, "Firstname cannot be empty");
        this.firstname = firstname;
    }

    private void setSurname(String surname) {
        assertArgumentNotEmpty(surname, "Surname cannot be empty");
        this.surname = surname;
    }

    private void setEmail(String email) {
        assertArgumentNotEmpty(email, "Email cannot be empty");
        this.email = email;
    }

    // Accessors
    public Identity id() { return id; }
    public String username() { return username; }
    public String password() { return password; }
    public String firstname() { return firstname; }
    public String surname() { return surname; }
    public String email() { return email; }
    public Role roleId() { return roleJpa; }
//    public TeamJpa teamId() { return teamJpa; }
}

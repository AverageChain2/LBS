package staffs.common.security;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity(name = "app_user")
@Table(name = "app_user")
@Getter
@Setter
public class AppUserJpa {

    public static final String EMAIL = "email";
    public static final String FIRST_NAME = "first_name";
    public static final String ID = "id";
    public static final String PASSWORD = "password";
    public static final String ROLE = "role";
    public static final String TEAM = "team";
    public static final String SURNAME = "surname";
    public static final String USERNAME = "username";

    @Id
    @Column(name = ID)
    private String userUUID;

    @NotNull
    @Column(name = USERNAME, unique = true)
    private String userName;

    @NotNull
    @Column(name = PASSWORD)
    private String password;

    @NotNull
    @Column(name = EMAIL, unique = true)
    private String email;

    @NotNull
    @Column(name = FIRST_NAME)
    private String firstName;

    @NotNull
    @Column(name = SURNAME)
    private String surname;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private RoleJpa role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private TeamJpa team;

    public AppUserJpa() {
    }

    protected AppUserJpa(String userUUID, String userName, String password, String firstName,
                         String surname, String email, RoleJpa role, TeamJpa team) {
        this.userUUID = userUUID;
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.surname = surname;
        this.email = email;
        this.role = role;
        this.team = team;
    }

    public static AppUserJpa of(String userUUID, String userName, String password, String firstName,
                                String surname, String email, RoleJpa role, TeamJpa team) {
        return new AppUserJpa(userUUID, userName, password, firstName, surname, email, role, team);
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s, %s, %s, %s", userUUID, userName, password, email, role, team);
    }

    // Factory method
    public static AppUserJpa appUserJpaOf(String id, String userName, String password, String firstName, String surname, String email, RoleJpa role, TeamJpa team) {
        return new AppUserJpa(id, userName, password, firstName, surname, email, role, team );
    }
}

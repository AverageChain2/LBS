package staffs.staffleave.infrastructure.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import staffs.staffleave.domain.user.UserRole;


@Entity(name = "users")
@Table(name = "users")
@ToString
@Getter
@Setter
public class UserJpa {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "surname")
    private String surname;

    @Column(name = "role")
    private UserRole role;

    @Column(name = "team_id")
    private String teamID;

    public UserJpa() {
    }

    protected UserJpa(String id, String firstname, String surname, UserRole role, String teamID) {
        this.id = id;
        this.firstname = firstname;
        this.surname = surname;
        this.role = role;
        this.teamID = teamID;
    }

    public static UserJpa of(String id, String firstname, String surname, UserRole role, String teamID) {
        return new UserJpa(id, firstname, surname, role, teamID);
    }
}

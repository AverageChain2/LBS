package staffs.staffleave.infrastructure.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


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
    private String role;

    @Column(name = "team")
    private String team;

    public UserJpa() {
    }

    protected UserJpa(String id, String firstname, String surname, String role, String team) {
        this.id = id;
        this.firstname = firstname;
        this.surname = surname;
        this.role = role;
        this.team = team;
    }

    public static UserJpa of(String id, String firstname, String surname, String role, String team) {
        return new UserJpa(id, firstname, surname, role, team);
    }
}

package staffs.staffleave.domain.user;

import lombok.ToString;
import staffs.common.domain.Entity;
import staffs.common.domain.Identity;
import staffs.staffleave.domain.events.NewUserAddedEvent;

@ToString
public class User extends Entity {

    private String firstname;
    private String surname;
    private final String role;
    private final String team;

    public User(Identity id, String firstname, String surname, String role, String team) {
        super(id);
        setFirstname(firstname);
        setSurname(surname);
        this.role = role;
        this.team = team;
    }

    public static User UserOfWithEvent(Identity id, String firstname, String surname, String role, String team) {
        User newUser = new User(id, firstname, surname, role, team);
        newUser.addDomainEvent(new NewUserAddedEvent(newUser.id.id(), firstname, surname, role, team));
        return newUser;
    }

    // Factory method for testing or creation
    public static User userOf(Identity id, String firstname, String surname, String role, String teamID) {
        return new User(id, firstname, surname, role, teamID);
    }

    private void setFirstname(String firstname) {
        assertArgumentNotEmpty(firstname, "Firstname cannot be empty");
        this.firstname = firstname;
    }

    private void setSurname(String surname) {
        assertArgumentNotEmpty(surname, "Surname cannot be empty");
        this.surname = surname;
    }

    // Domain-style accessors
    public Identity id() {
        return id;
    }

    public String firstname() {
        return firstname;
    }

    public String surname() {
        return surname;
    }

    public String role() {
        return role;
    }

    public String team() {
        return team;
    }
}

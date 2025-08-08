package staffs.staffleave.domain.user;

import lombok.ToString;
import staffs.common.domain.Entity;
import staffs.common.domain.Identity;

@ToString
public class User extends Entity {

    private String firstname;
    private String surname;
    private final UserRole role;
    private final String teamID;

    public User(Identity id, String firstname, String surname, UserRole role, String teamID) {
        super(id);
        setFirstname(firstname);
        setSurname(surname);
        this.role = role;
        this.teamID = teamID;
    }

    // Factory method for testing or creation
    public static User userOf(Identity id, String firstname, String surname, UserRole role, String teamID) {
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

    public UserRole role() {
        return role;
    }

    public String teamID() {
        return teamID;
    }
}

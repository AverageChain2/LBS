package staffs.identitymanagement.appUser;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import staffs.common.domain.Identity;
import staffs.common.security.Role;
import staffs.common.security.Team;
import staffs.identitymanagement.domain.events.events.NewAppUserAddedEvent;
import staffs.identitymanagement.domain.user.AppUserDomain;

import static org.junit.jupiter.api.Assertions.*;

public class AppUserAggregateTests {

    private final Identity VALID_ID = new Identity("user001");
    private final String VALID_USERNAME = "cjones";
    private final String VALID_PASSWORD = "securePass123";
    private final String VALID_FIRSTNAME = "Caden";
    private final String VALID_SURNAME = "Jones";
    private final String VALID_EMAIL = "cjones@example.com";
    private final Role VALID_ROLE = new Role(); // Assuming default constructor is fine
    private final Team VALID_TEAM = new Team(); // Assuming default constructor is fine

    @Test
    @DisplayName("App User is created successfully with valid details")
    void test01_createAppUserDomain() {
        AppUserDomain user = AppUserDomain.userOf(
                VALID_ID, VALID_USERNAME, VALID_PASSWORD,
                VALID_FIRSTNAME, VALID_SURNAME, VALID_EMAIL,
                VALID_ROLE, VALID_TEAM
        );

        assertNotNull(user);
        assertEquals(VALID_ID, user.id());
        assertEquals(VALID_USERNAME, user.username());
        assertEquals(VALID_PASSWORD, user.password());
        assertEquals(VALID_FIRSTNAME, user.firstname());
        assertEquals(VALID_SURNAME, user.surname());
        assertEquals(VALID_EMAIL, user.email());
        assertEquals(VALID_ROLE, user.roleId());
        assertEquals(VALID_TEAM, user.teamId());
    }

    @Test
    @DisplayName("AppUserOfWithEvent should generate NewAppUserAddedEvent")
    void test02_eventGeneratedOnCreation() {
        AppUserDomain user = AppUserDomain.AppUserOfWithEvent(
                VALID_ID, VALID_USERNAME, VALID_PASSWORD,
                VALID_FIRSTNAME, VALID_SURNAME, VALID_EMAIL,
                VALID_ROLE, VALID_TEAM
        );

        assertNotNull(user);
        assertFalse(user.listOfDomainEvents().isEmpty());
        assertTrue(user.listOfDomainEvents().get(0) instanceof NewAppUserAddedEvent);

        NewAppUserAddedEvent event = (NewAppUserAddedEvent) user.listOfDomainEvents().get(0);
        assertEquals(VALID_ID.id(), event.getAggregateId());
        assertEquals(VALID_FIRSTNAME, event.getFirstname());
        assertEquals(VALID_SURNAME, event.getSurname());
        assertEquals(VALID_ROLE.getType(), event.getRole());
        assertEquals(VALID_TEAM.getName(), event.getTeam());
    }
}

package staffs.identitymanagement.ui;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AppUserDetailsRequest {
    private String username;
    private String password;
}
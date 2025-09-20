package staffs.identitymanagement.application;

import staffs.common.domain.Identity;
import staffs.common.security.AppUser;
import staffs.common.security.Role;
import staffs.identitymanagement.application.DTO.AppUserDTO;
import staffs.identitymanagement.domain.user.AppUserDomain;

public class AppUserMapper {

    // JPA to DTO
    public static AppUserDTO jpaToDTO(AppUser jpa) {
        return new AppUserDTO(
                jpa.getUserUUID(),
                jpa.getUserName(),
                jpa.getPassword(),
                jpa.getFirstName(),
                jpa.getSurname(),
                jpa.getEmail(),
                jpa.getRole().getId()
                // jpa.getTeam().getId() // Uncomment if Team is added
        );
    }

    // Domain to JPA
    public static AppUser domainToJpa(AppUserDomain domain) {
        return AppUser.appUserJpaOf(
                domain.id().id(),
                domain.username(),
                domain.password(),
                domain.firstname(),
                domain.surname(),
                domain.email(),
                domain.roleId()
//                domain.teamId()
        );
    }

    // JPA to Domain
    public static staffs.common.security.AppUser jpaToDomain(AppUser jpa) {
        return new staffs.common.security.AppUser(
                jpa.getUserUUID(),
                jpa.getUserName(),
                jpa.getPassword(),
                jpa.getFirstName(),
                jpa.getSurname(),
                jpa.getEmail(),
                jpa.getRole()
                // jpa.getTeam()
        );
    }

//    // DTO to Domain
//    public static staffs.common.security.AppUser dtoToDomain(AppUserDTO dto) {
//        return new staffs.common.security.AppUser(
//                new Identity(dto.getId()),
//                dto.getUsername(),
//                dto.getPassword(),
//                dto.getFirstName(),
//                dto.getSurname(),
//                dto.getEmail(),
//                new Role(dto.getRoleID())
//                // new Team(dto.getTeamID())
//        );
//    }
}

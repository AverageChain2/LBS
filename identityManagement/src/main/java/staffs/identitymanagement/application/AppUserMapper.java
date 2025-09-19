package staffs.identitymanagement.application;

import staffs.common.domain.Identity;
import staffs.common.security.AppUserJpa;
import staffs.identitymanagement.application.DTO.AppUserDTO;
import staffs.identitymanagement.domain.user.AppUser;

public class AppUserMapper {

    // Domain to DTO
    public static AppUserDTO toUserDTO(AppUserJpa appUserJpa) {
        return new AppUserDTO(
                appUserJpa.getUserUUID(),
                appUserJpa.getUserName(),
                appUserJpa.getPassword(),
                appUserJpa.getFirstName(),
                appUserJpa.getSurname(),
                appUserJpa.getEmail(),
                appUserJpa.getRole().getId(),
                appUserJpa.getTeam().getId()
        );
    }

    // Domain to JPA
    public static AppUserJpa toJpa(AppUser appUser) {
        return AppUserJpa.appUserJpaOf(
        appUser.id().id(),
        appUser.username(),
        appUser.password(),
        appUser.firstname(),
        appUser.surname(),
        appUser.email(),
        appUser.roleId(),
        appUser.teamId()
        );
    }

//    // DTO to Domain
//    public static AppUser toDomain(AppUserDTO dto) {
//        return new AppUser(
//                dto.getId(),
//                dto.getUsername(),
//                dto.getPassword(),
//                dto.getFirstName(),
//                dto.getSurname(),
//                dto.getEmail(),
//                dto.getRoleID(),
//                dto.getTeamID()
//        );
//    }

    // JPA to Domain
    public static AppUser toDomain(AppUserJpa jpa) {
        return new AppUser(
                new Identity(jpa.getUserUUID()),
                jpa.getUserName(),
                jpa.getPassword(),
                jpa.getFirstName(),
                jpa.getSurname(),
                jpa.getEmail(),
                jpa.getRole(),
                jpa.getTeam()
        );
    }
}

package staffs.staffleave.application.user;


import staffs.staffleave.application.user.DTO.UserDTO;
import staffs.staffleave.domain.user.User;
import staffs.staffleave.infrastructure.user.UserJpa;

public class UserMapper {

    // Domain to DTO
    public static UserDTO toUserDTO(UserJpa user) {
        return new UserDTO(
                user.getId(),
                user.getFirstname(),
                user.getSurname(),
                user.getRole(),
                user.getTeamID()
        );
    }

    // Domain to JPA
    public static UserJpa toJpa(User user) {
        UserJpa jpa = new UserJpa();
        jpa.setId(user.id().id());
        jpa.setFirstname(user.firstname());
        jpa.setSurname(user.surname());
        jpa.setRole(user.role());
        jpa.setTeamID(user.teamID());
        return jpa;
    }

    // DTO to Domain
//    public static User toDomain(UserDTO dto) {
//        return new User(
//                dto.getId(),
//                dto.getFullname_firstname(),
//                dto.getFullname_surname(),
//                dto.getRole(),
//                dto.getTeamID()
//        );
//    }



    // JPA to Domain
//    public static User toDomain(UserJpa jpa) {
//        return new User(
//                jpa.getId(),
//                jpa.getFirstname(),
//                jpa.getSurname(),
//                jpa.getRole(),
//                jpa.getTeamID()
//        );
//    }
}

package tech.salvas.eifapi.mappers;

import org.mapstruct.Mapper;
import tech.salvas.eifapi.dtos.UserDTO;
import tech.salvas.eifapi.models.Admin;

@Mapper(componentModel = "spring")
public interface IUserDTOAdminMapper {
   UserDTO adminToUserDTO(Admin admin);
   Admin userDTOToAdmin(UserDTO userDTO);
}

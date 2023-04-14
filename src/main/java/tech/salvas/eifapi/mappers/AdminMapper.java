package tech.salvas.eifapi.mappers;

import tech.salvas.eifapi.dtos.UserDTO;
import tech.salvas.eifapi.models.Admin;

public class AdminMapper implements Mapper<Admin, UserDTO> {
    @Override
    public UserDTO toDTO(Admin admin) {
        var dto = new UserDTO();
        dto.setAdmin(true);
        dto.setCp(admin.getCe());
        dto.setFirst_name(admin.getFirst_name());
        dto.setLast_name(admin.getLast_name());
        return dto;
    }

    @Override
    public Admin toEntity(UserDTO userDTO) {
        var entity = new Admin();
        entity.setCe(userDTO.getCp());
        entity.setFirst_name(userDTO.getFirst_name());
        entity.setLast_name(userDTO.getLast_name());
        return entity;
    }
}

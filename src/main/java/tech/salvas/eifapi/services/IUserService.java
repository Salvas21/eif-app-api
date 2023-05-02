package tech.salvas.eifapi.services;

import tech.salvas.eifapi.dtos.UserDTO;

import java.util.List;

public interface IUserService {
    void save();

    void delete(String key);

    void update();

    List<UserDTO> getAll();

    UserDTO get(String key);
}

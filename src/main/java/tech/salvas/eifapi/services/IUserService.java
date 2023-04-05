package tech.salvas.eifapi.services;

import tech.salvas.eifapi.dtos.UserDTO;

import java.util.List;

// Let me know if you like this interface for crud services otherwise we can make individual ones for each service
public interface IUserService {
    void save();
    void delete(String key);
    void update();
    List<UserDTO> getAll();
    UserDTO get(String key);
    UserDTO getAt(int index);
}

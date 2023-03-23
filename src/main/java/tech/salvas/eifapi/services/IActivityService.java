package tech.salvas.eifapi.services;

import tech.salvas.eifapi.dto.ActivityDTO;

import java.util.List;

public interface IActivityService {
    void save();
    void delete();

    void update();
    List<ActivityDTO> getAll();
    ActivityDTO get(String code);
}

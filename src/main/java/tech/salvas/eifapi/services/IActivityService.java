package tech.salvas.eifapi.services;

import tech.salvas.eifapi.dtos.ActivityDTO;

import java.util.List;

public interface IActivityService {
    void save(ActivityDTO activityDTO);

    boolean delete(String code);

    boolean update(ActivityDTO activityDTO, String code);

    List<ActivityDTO> getAll();

    ActivityDTO get(String code);

    ActivityDTO getById(Long id);

    List<ActivityDTO> getCurrentForStudent(String cp);

    List<ActivityDTO> getActivityAvailableForStudent(String cp);

    List<String> getLevels();
}

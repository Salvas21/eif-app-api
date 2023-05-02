package tech.salvas.eifapi.mappers;

import tech.salvas.eifapi.dtos.ActivityDTO;
import tech.salvas.eifapi.models.Activity;

public class ActivityMapper implements Mapper<Activity, ActivityDTO> {
    public ActivityDTO toDTO(Activity activity) {
        var dto = new ActivityDTO();
        dto.setId(activity.getId());
        dto.setCode(activity.getCode());
        dto.setName(activity.getName());
        dto.setLevel(activity.getLevelId());
        dto.setDescription(activity.getDescription());
        dto.setPlaces(activity.getPlaces());
        dto.setLevelName();
        return dto;
    }

    public Activity toEntity(ActivityDTO activityDTO) {
        var entity = new Activity();
        entity.setId(activityDTO.getId());
        entity.setCode(activityDTO.getCode());
        entity.setName(activityDTO.getName());
        entity.setLevelId(getLevelIdFromName(activityDTO.getLevelName()));
        entity.setDescription(activityDTO.getDescription());
        entity.setPlaces(activityDTO.getPlaces());
        return entity;
    }

    private int getLevelIdFromName(String levelName) {
        if (levelName.equalsIgnoreCase("Débutant")) {
            return 1;
        }
        if (levelName.equalsIgnoreCase("Intermédiaire")) {
            return 2;
        }
        return 3;
    }
}

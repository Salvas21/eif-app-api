package tech.salvas.eifapi.mappers;

import tech.salvas.eifapi.dtos.ActivityDTO;
import tech.salvas.eifapi.models.Activity;

public class ActivityMapper implements Mapper<Activity, ActivityDTO> {
    public ActivityDTO toDTO(Activity activity) {
        var dto = new ActivityDTO();
        dto.setCode(activity.getCode());
        dto.setName(activity.getName());
        dto.setLevel(activity.getLevelId());
        dto.setDescription(activity.getDescription());
        return dto;
    }
    public Activity toEntity(ActivityDTO activityDTO) {
        var entity = new Activity();
        entity.setCode(activityDTO.getCode());
        entity.setName(activityDTO.getName());
        entity.setLevelId(activityDTO.getLevel());
        entity.setDescription(activityDTO.getDescription());
        return entity;
    }
}

package tech.salvas.eifapi.mappers;

import tech.salvas.eifapi.dtos.ActivityDTO;
import tech.salvas.eifapi.models.Activity;

public interface IActivityDTOActivityMapper {
    ActivityDTO activityToActivityDTO(Activity activity);
    Activity activityDTOToActivity(ActivityDTO activityDTO);
}

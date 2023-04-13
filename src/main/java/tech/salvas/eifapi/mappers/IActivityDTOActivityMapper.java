package tech.salvas.eifapi.mappers;

import org.mapstruct.Mapper;
import tech.salvas.eifapi.dtos.ActivityDTO;
import tech.salvas.eifapi.models.Activity;

@Mapper(componentModel = "spring")
public interface IActivityDTOActivityMapper {
    ActivityDTO activityToActivityDTO(Activity activity);
    Activity activityDTOToActivity(ActivityDTO activityDTO);
}

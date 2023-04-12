package tech.salvas.eifapi.dtos;

import lombok.Data;
import tech.salvas.eifapi.models.Activity;

@Data
public class ActivityDTO {

    private String code;
    private String name;
    private String description;
    private int level;

    public ActivityDTO(Activity activity) {
        this.code = activity.getCode();
        this.name = activity.getName();
        this.description = activity.getDescription();
        this.level = activity.getActivityLevel();
    }

    public ActivityDTO() {}
}

package tech.salvas.eifapi.dto;

import lombok.Data;
import tech.salvas.eifapi.model.Activity;

@Data
public class ActivityDTO {

    private String code;
    private String name;
    private String description;
    private String level;

    public ActivityDTO(Activity activity) {
        this.code = activity.getCode();
        this.name = activity.getName();
        this.description = activity.getDescription();
        this.level = activity.getLevel();
    }

    public ActivityDTO() {}
}

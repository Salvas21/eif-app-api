package tech.salvas.eifapi.dtos;

import lombok.Data;
import tech.salvas.eifapi.models.Activity;

@Data
public class ActivityDTO {

    private String code;
    private String name;
    private String description;
    private int level;

    private String levelName = "";

    public ActivityDTO(Activity activity) {
        this.code = activity.getCode();
        this.name = activity.getName();
        this.description = activity.getDescription();
        this.level = activity.getLevelId();
        changeLevelName();
    }

    public ActivityDTO() {}

    public void setLevelName() {
        changeLevelName();
    }

    public String getLevelName() {
        return this.levelName;
    }

    private void changeLevelName() {
        if (level == 1) {
            this.levelName = "Débutant";
        }
        if (level == 2) {
            this.levelName = "Intermédiaire";
        }
        if (level == 3) {
            this.levelName = "Avancé";
        }
    }
}

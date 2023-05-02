package tech.salvas.eifapi.dtos;

import lombok.Data;

@Data
public class ActivityDTO {
    private Long id;
    private String code;
    private String name;
    private String description;
    private int level;
    private int places;
    private String levelName = "";

    public ActivityDTO() {
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName() {
        changeLevelName();
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

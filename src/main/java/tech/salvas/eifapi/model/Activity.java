package tech.salvas.eifapi.model;

import lombok.Data;

@Data
public class Activity {
    private String code;
    private String name;
    private String description;
    private String level;

    private int activityLevel;

    public Activity(String code, String name, String description, String level) {
        this.code = code;
        this.description = description;
        this.name = name;
        this.level = level;
        if (level.equals("Débutant"))
            activityLevel = 0;
        if (level.equals("Intermédiaire"))
            activityLevel = 1;
        if (level.equals("Avancé"))
            activityLevel = 2;
    }

    public Activity() {};
}

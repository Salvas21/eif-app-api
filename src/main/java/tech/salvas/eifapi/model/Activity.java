package tech.salvas.eifapi.model;

import lombok.Data;

@Data
public class Activity {
    private String code;
    private String name;
    private String description;
    private String level;

    public Activity(String code, String name, String description, String level) {
        this.code = code;
        this.description = description;
        this.name = name;
        this.level = level;
    }

    public Activity() {};
}

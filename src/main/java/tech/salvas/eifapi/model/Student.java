package tech.salvas.eifapi.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class Student extends User {
    private int level;
    private Activity[] activities;

    public Student(String fName, String lName, String cp, int level, Activity[] activities) {
        super(fName, lName, cp, false);
        this.level = level;
        this.activities = activities;
    }
}

package tech.salvas.eifapi.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import tech.salvas.eifapi.model.Activity;
import tech.salvas.eifapi.model.Student;
import tech.salvas.eifapi.model.User;

import java.util.ArrayList;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class StudentDTO extends UserDTO {
    private int level;
    private ActivityDTO[] activities;

    public StudentDTO(User user, int level, Activity[] activities) {
        super(user);
        this.level = level;

        if (activities.length > 0) {
            ArrayList<ActivityDTO> temp = new ArrayList<>();
            for (Activity activity : activities)
                temp.add(new ActivityDTO(activity));

            this.activities = temp.toArray(new ActivityDTO[0]);
        }
    }

    public StudentDTO(Student student) {
        this(student, student.getLevel(), student.getActivities());
    }
}

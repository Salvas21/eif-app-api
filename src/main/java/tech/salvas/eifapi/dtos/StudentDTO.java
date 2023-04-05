package tech.salvas.eifapi.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import tech.salvas.eifapi.models.Choice;
import tech.salvas.eifapi.models.Student;
import tech.salvas.eifapi.models.User;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class StudentDTO extends UserDTO {
    private int level;
    private List<ChoiceDTO> choices;

    public StudentDTO(User user, int level, List<Choice> choices) {
        super(user);
        this.level = level;

        if (choices.size() > 0) {
            ArrayList<ChoiceDTO> temp = new ArrayList<>();
            for (Choice choice : choices)
                temp.add(new ChoiceDTO(choice));

            this.choices = temp.stream().toList();
        }
    }

    public StudentDTO(Student student) {
        this(student, student.getLevel(), student.getChoices());
    }
}

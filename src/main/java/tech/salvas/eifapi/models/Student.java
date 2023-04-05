package tech.salvas.eifapi.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class Student extends User {
    private int level;
    private List<Choice> choices;

    public Student(String fName, String lName, String cp, int level, Choice[] choices) {
        super(fName, lName, cp, false);
        this.level = level;
        this.choices = new ArrayList<>();
        this.choices.addAll(Arrays.stream(choices).toList());
    }
}

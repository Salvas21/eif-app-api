package tech.salvas.eifapi.dtos;

import lombok.Data;
import tech.salvas.eifapi.models.Activity;
import tech.salvas.eifapi.models.Choice;

@Data
public class ChoiceDTO {
    private ActivityDTO activity;
    private StudentDTO student;
    private int preference;
    private boolean selected;

    public ChoiceDTO(Choice choice) {
        this.activity = new ActivityDTO(choice.getActivity());
        this.student = new StudentDTO(choice.getStudentId());
        this.preference = choice.getPreference();
        this.selected = choice.isSelected();
    }
}

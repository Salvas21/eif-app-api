package tech.salvas.eifapi.dto;

import lombok.Data;
import tech.salvas.eifapi.model.Activity;
import tech.salvas.eifapi.model.Choice;
import tech.salvas.eifapi.model.Student;

@Data
public class ChoiceDTO {
    private Activity activity;
    private int preference;
    private boolean selected;

    public ChoiceDTO(Choice choice) {
        this.activity = choice.getActivity();
        this.preference = choice.getPreference();
        this.selected = choice.isSelected();
    }
}

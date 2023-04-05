package tech.salvas.eifapi.dtos;

import lombok.Data;
import tech.salvas.eifapi.models.Activity;
import tech.salvas.eifapi.models.Choice;

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

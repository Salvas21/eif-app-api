package tech.salvas.eifapi.dtos;

import lombok.Data;

@Data
public class ChoiceActivityDTO {
    private ActivityDTO activity;
    private int preference;
    private boolean selected;
}

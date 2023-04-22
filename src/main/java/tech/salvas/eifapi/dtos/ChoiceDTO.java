package tech.salvas.eifapi.dtos;

import lombok.Data;
import tech.salvas.eifapi.models.Activity;
import tech.salvas.eifapi.models.Choice;

@Data
public class ChoiceDTO {
    private Long activityId;
    private Long studentId;
    private int preference;
    private boolean selected;
}

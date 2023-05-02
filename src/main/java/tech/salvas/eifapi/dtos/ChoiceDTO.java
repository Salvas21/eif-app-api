package tech.salvas.eifapi.dtos;

import lombok.Data;

@Data
public class ChoiceDTO {
    private Long activityId;
    private Long studentId;
    private int preference;
    private boolean selected = false;
}

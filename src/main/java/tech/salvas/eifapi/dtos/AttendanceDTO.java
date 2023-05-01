package tech.salvas.eifapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceDTO {
    private Long activityId;
    private Long studentId;
    private String session = "";
    private boolean finished = false;
}

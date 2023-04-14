package tech.salvas.eifapi.dtos;

import lombok.Data;;

@Data
public class AttendanceDTO {
    private ActivityDTO activity;
    private String session = "";
}

package tech.salvas.eifapi.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Attendance {
    @Id
    private Long id;
    private long activityId;
    private long studentId;
    private String session = "";
    private boolean finished = false;
}

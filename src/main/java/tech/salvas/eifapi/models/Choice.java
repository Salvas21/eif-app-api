package tech.salvas.eifapi.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Choice {
    @Id
    private Long id;
    private long activityId;
    private long studentId;
    private int preference;
    private LocalDateTime dateSubmitted = LocalDateTime.now();
}

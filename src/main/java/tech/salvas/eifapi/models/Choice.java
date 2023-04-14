package tech.salvas.eifapi.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.hibernate.Hibernate;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Choice {
    @Id
    private Long id;
    private Long activity;
    private Long studentId;
    private int preference;
    private boolean selected;
    private LocalDateTime dateSubmitted = LocalDateTime.now();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Choice choice = (Choice) o;
        return getId() != null && Objects.equals(getId(), choice.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

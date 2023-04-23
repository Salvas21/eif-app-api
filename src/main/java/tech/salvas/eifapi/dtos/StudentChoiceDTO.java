package tech.salvas.eifapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentChoiceDTO {
    private StudentDTO student;
    private List<ChoiceActivityDTO> choices;
}

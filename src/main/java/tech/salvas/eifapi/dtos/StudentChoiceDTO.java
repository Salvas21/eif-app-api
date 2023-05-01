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

    public AttendanceDTO getAttendance() {
        ChoiceActivityDTO selected = null;
        for (ChoiceActivityDTO choice : choices) {
            if (choice.isSelected())
                selected = choice;
        }

        if (selected != null)
            return new AttendanceDTO(selected.getActivity().getId(), student.getId(), "", false);

        return null;
    }
}

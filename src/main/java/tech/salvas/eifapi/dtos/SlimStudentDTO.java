package tech.salvas.eifapi.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SlimStudentDTO {
    private long id;
    private String first_name;
    private String last_name;
    private String cp;

    public SlimStudentDTO(StudentDTO studentDTO) {
        this.cp = studentDTO.getCp();
        this.first_name = studentDTO.getFirst_name();
        this.last_name = studentDTO.getFirst_name();
        this.id = studentDTO.getId();
    }
}

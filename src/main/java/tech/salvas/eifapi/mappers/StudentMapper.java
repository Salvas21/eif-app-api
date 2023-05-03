package tech.salvas.eifapi.mappers;

import tech.salvas.eifapi.dtos.StudentDTO;
import tech.salvas.eifapi.models.Student;

public class StudentMapper implements Mapper<Student, StudentDTO> {
    @Override
    public StudentDTO toDTO(Student student) {
        var dto = new StudentDTO();
        dto.setAdmin(false);
        dto.setEmail(student.getEmail());
        dto.setFirst_name(student.getFirstName());
        dto.setLast_name(student.getLastName());
        dto.setCp(student.getCp());
        dto.setId(student.getId());
        return dto;
    }

    @Override
    public Student toEntity(StudentDTO studentDTO) {
        var student = new Student();
        student.setEmail(studentDTO.getEmail());
        student.setFirstName(studentDTO.getFirst_name());
        student.setLastName(studentDTO.getLast_name());
        student.setCp(studentDTO.getCp());
        student.setId(studentDTO.getId());
        return student;
    }
}

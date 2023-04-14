package tech.salvas.eifapi.mappers;

import tech.salvas.eifapi.dtos.StudentDTO;
import tech.salvas.eifapi.models.Student;

public class StudentMapper implements Mapper<Student, StudentDTO> {
    @Override
    public StudentDTO toDTO(Student student) {
        var dto = new StudentDTO();
        dto.setAdmin(false);
        dto.setFirst_name(student.getFirst_name());
        dto.setLast_name(student.getLast_name());
        dto.setCp(student.getCp());
        return dto;
    }

    @Override
    public Student toEntity(StudentDTO studentDTO) {
        var student = new Student();
        student.setFirst_name(studentDTO.getFirst_name());
        student.setLast_name(studentDTO.getLast_name());
        student.setCp(studentDTO.getCp());
        return student;
    }
}

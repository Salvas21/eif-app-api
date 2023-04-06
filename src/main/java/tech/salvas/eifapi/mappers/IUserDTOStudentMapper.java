package tech.salvas.eifapi.mappers;

import org.mapstruct.Mapper;
import tech.salvas.eifapi.dtos.StudentDTO;
import tech.salvas.eifapi.dtos.UserDTO;
import tech.salvas.eifapi.models.Student;

@Mapper(componentModel = "spring")
public interface IUserDTOStudentMapper {
   UserDTO studentToUserDTO(Student student);
   StudentDTO studentToStudentDTO(Student student);
   Student userDTOToStudent(UserDTO userDTO);
   Student studentDTOToStudent(StudentDTO studentDTO);
}

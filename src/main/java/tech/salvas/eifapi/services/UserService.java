package tech.salvas.eifapi.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.salvas.eifapi.dtos.ChoiceDTO;
import tech.salvas.eifapi.dtos.StudentDTO;
import tech.salvas.eifapi.dtos.UserDTO;
import tech.salvas.eifapi.mappers.IChoiceDTOChoiceMapper;
import tech.salvas.eifapi.mappers.IUserDTOAdminMapper;
import tech.salvas.eifapi.mappers.IUserDTOStudentMapper;
import tech.salvas.eifapi.models.Admin;
import tech.salvas.eifapi.models.Choice;
import tech.salvas.eifapi.models.Student;
import tech.salvas.eifapi.repositories.AdminRepository;
import tech.salvas.eifapi.repositories.ChoiceRepository;
import tech.salvas.eifapi.repositories.StudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@AllArgsConstructor
@Service
public class UserService implements IUserService {
    private AdminRepository adminRepository;
    private StudentRepository studentRepository;
    private ChoiceRepository choiceRepository;
    private IUserDTOStudentMapper studentMapper;
    private IUserDTOAdminMapper adminMapper;
    private IChoiceDTOChoiceMapper choiceMapper;

    @Override
    public void save() {

    }

    @Override
    public void delete(String key) {

    }

    @Override
    public void update() {

    }

    @Override
    public List<UserDTO> getAll() {
        List<UserDTO> users = new ArrayList<>();
        for (Student student : studentRepository.findAll())
            users.add(studentMapper.studentToUserDTO(student));
        for (Admin admin : adminRepository.findAll())
            users.add(adminMapper.adminToUserDTO(admin));
        return users;
    }

    @Override
    public UserDTO get(String key) {
        return null;
    }

    @Override
    public UserDTO getAt(int index) {
        return null;
    }

    public List<StudentDTO> getStudents() {
        List<StudentDTO> studentsDTO = new ArrayList<>();
        for (Student student : studentRepository.findStudents().orElseThrow()) {
            var studentDTO = studentMapper.studentToStudentDTO(student);
            List<ChoiceDTO> choicesDTO = new ArrayList<>();
            for (Choice choice : choiceRepository.findChoicesByStudentId(student).orElseThrow())
                choicesDTO.add(choiceMapper.choiceToChoiceDTO(choice));
            studentDTO.setChoices(choicesDTO);
            studentsDTO.add(studentDTO);
        }
        return studentsDTO;
    }

    public StudentDTO getRandomStudent() {
        List<StudentDTO> students = getStudents();
        return students.get(new Random().nextInt(students.size()));
    }

    public UserDTO getAdmin() {
        Admin admin = adminRepository.findAdmins().orElseThrow().get(0);
        return adminMapper.adminToUserDTO(admin);
    }
}

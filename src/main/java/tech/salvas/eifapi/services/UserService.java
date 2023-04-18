package tech.salvas.eifapi.services;

import org.springframework.stereotype.Service;
import tech.salvas.eifapi.dtos.ChoiceDTO;
import tech.salvas.eifapi.dtos.StudentDTO;
import tech.salvas.eifapi.dtos.UserDTO;
import tech.salvas.eifapi.mappers.AdminMapper;
import tech.salvas.eifapi.mappers.ChoiceMapper;
import tech.salvas.eifapi.mappers.StudentMapper;
import tech.salvas.eifapi.models.Admin;
import tech.salvas.eifapi.models.Choice;
import tech.salvas.eifapi.models.Student;
import tech.salvas.eifapi.repositories.AdminRepository;
import tech.salvas.eifapi.repositories.ChoiceRepository;
import tech.salvas.eifapi.repositories.StudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class UserService implements IUserService {
    private AdminRepository adminRepository;
    private StudentRepository studentRepository;
    private ChoiceRepository choiceRepository;
    private StudentMapper studentMapper = new StudentMapper();
    private AdminMapper adminMapper = new AdminMapper();
    private ChoiceMapper choiceMapper = new ChoiceMapper();

    public UserService(AdminRepository adminRepository, StudentRepository studentRepository, ChoiceRepository choiceRepository) {
        this.adminRepository = adminRepository;
        this.studentRepository = studentRepository;
        this.choiceRepository = choiceRepository;
    }

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
//        for (Student student : studentRepository.findAll())
//            users.add(studentMapper.studentToUserDTO(student));
//        for (Admin admin : adminRepository.findAll())
//            users.add(adminMapper.adminToUserDTO(admin));
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
        for (Student student : studentRepository.findAll()) {
            var studentDTO = studentMapper.toDTO(student);
            List<ChoiceDTO> choicesDTO = new ArrayList<>();
            for (Choice choice : choiceRepository.findChoicesByStudentId(student.getId()).orElseThrow())
                choicesDTO.add(choiceMapper.toDTO(choice));
            studentDTO.setChoices(choicesDTO);
            studentsDTO.add(studentDTO);
        }
        return studentsDTO;
    }

    public StudentDTO getStudent(String email, String password) {
        //todo: Check for cp or email
        var student = studentRepository.findStudentByEmailAndPassword(email, password).orElseThrow();
        return studentMapper.toDTO(student);
//        List<StudentDTO> students = getStudents();
//        return students.get(new Random().nextInt(students.size()));
    }

    public UserDTO getAdmin() {
        Admin admin = adminRepository.findAll().iterator().next();
        return adminMapper.toDTO(admin);
    }
}

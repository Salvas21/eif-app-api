package tech.salvas.eifapi.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tech.salvas.eifapi.configs.security.AppUserDetails;
import tech.salvas.eifapi.configs.security.JwtService;
import tech.salvas.eifapi.dtos.AuthDTO;
import tech.salvas.eifapi.dtos.StudentDTO;
import tech.salvas.eifapi.dtos.UserDTO;
import tech.salvas.eifapi.mappers.AdminMapper;
import tech.salvas.eifapi.mappers.StudentMapper;
import tech.salvas.eifapi.models.Admin;
import tech.salvas.eifapi.models.Student;
import tech.salvas.eifapi.repositories.AdminRepository;
import tech.salvas.eifapi.repositories.StudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final AdminRepository adminRepository;
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper = new StudentMapper();
    private final AdminMapper adminMapper = new AdminMapper();

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

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
        return null;
    }

    @Override
    public UserDTO get(String key) {
        return null;
    }

    @Override
    public UserDTO getAt(int index) {
        return null;
    }

    public StudentDTO getStudentById(Long id) {
        var student = this.studentRepository.findStudentById(id).orElseThrow();
        return studentMapper.toDTO(student);
    }

    public List<StudentDTO> getStudents() {
        List<StudentDTO> studentsDTO = new ArrayList<>();
        for (Student student : studentRepository.findAll()) {
            var studentDTO = studentMapper.toDTO(student);
            studentsDTO.add(studentDTO);
        }
        return studentsDTO;
    }

    public AuthDTO authenticate(String subject, String password, boolean isAdmin) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(subject, password)
        );

        return isAdmin ? generateAdminResponse(subject) : generateStudentResponse(subject);
    }

    private AuthDTO generateAdminResponse(String subject) {
        var admin = adminRepository.findByEmailOrCe(subject, subject).orElseThrow();

        var appUserDetails = new AppUserDetails();
        appUserDetails.setRole("admin");

        if (subject.contains("@")) {
            appUserDetails.setEmail(admin.getEmail());
        } else {
            appUserDetails.setUsername(admin.getCe());
        }

        appUserDetails.setPassword(admin.getPassword());

        var token = jwtService.generateToken(appUserDetails);

        return new AuthDTO(token, adminMapper.toDTO(admin));
    }

    private AuthDTO generateStudentResponse(String subject) {
        var student = studentRepository.findByEmailOrCp(subject, subject).orElseThrow();

        var appUserDetails = new AppUserDetails();
        appUserDetails.setRole("student");

        if (subject.contains("@")) {
            appUserDetails.setEmail(student.getEmail());
        } else {
            appUserDetails.setUsername(student.getCp());
        }

        appUserDetails.setPassword(student.getPassword());

        var token = jwtService.generateToken(appUserDetails);

        return new AuthDTO(token, studentMapper.toDTO(student));
    }
}

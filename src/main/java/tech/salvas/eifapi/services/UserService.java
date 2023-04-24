package tech.salvas.eifapi.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tech.salvas.eifapi.configs.security.AppUserDetails;
import tech.salvas.eifapi.configs.security.JwtService;
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

    public StudentDTO getStudent(String email, String password) throws NoSuchElementException {
        var student = (email.contains("@")) ? studentRepository.findStudentByEmailAndPassword(email, password).orElseThrow() : studentRepository.findStudentByCpAndPassword(email, password).orElseThrow();
        return studentMapper.toDTO(student);
    }

    public UserDTO getAdmin(String email, String password) throws NoSuchElementException {
        var admin = (email.contains("@")) ? adminRepository.findAdminByEmailAndPassword(email, password).orElseThrow() : adminRepository.findAdminByCeAndPassword(email, password).orElseThrow() ;
        return adminMapper.toDTO(admin);
    }

    public void registerAdmin() {
        var admin = new Admin();
        admin.setCe("QWER12341234");
        admin.setEmail("admin@qwer.com");
        admin.setFirstName("Martin");
        admin.setLastName("Sandwich");
        admin.setPassword(passwordEncoder.encode("Omega123"));
        System.out.println("BONJOUR");
        adminRepository.save(admin);

        var appUserDetails = new AppUserDetails();
        appUserDetails.setRole("admin");
        appUserDetails.setUsername(admin.getCe());
        appUserDetails.setEmail(admin.getEmail());
        appUserDetails.setPassword(admin.getPassword());

        System.out.println(jwtService.generateToken(appUserDetails));
    }

    public void authenticateAdmin() {
        // set email = subject
        String email = "";
        String password = "";
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );
        // authenticated
        var admin = adminRepository.findByEmailOrCe(email, email).orElseThrow();

        var appUserDetails = new AppUserDetails();
        appUserDetails.setRole("admin");
        appUserDetails.setUsername(admin.getCe());
        appUserDetails.setEmail(admin.getEmail());
        appUserDetails.setPassword(admin.getPassword());

        System.out.println(jwtService.generateToken(appUserDetails));
    }
}

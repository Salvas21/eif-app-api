package tech.salvas.eifapi.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.salvas.eifapi.dtos.StudentDTO;
import tech.salvas.eifapi.dtos.UserDTO;
import tech.salvas.eifapi.models.Student;
import tech.salvas.eifapi.models.User;
import tech.salvas.eifapi.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@AllArgsConstructor
@Service
public class UserService implements IUserService {
    private UserRepository userRepository;

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
        for (User user : userRepository.getUsers())
            users.add(new UserDTO(user));
        return users;
    }

    @Override
    public UserDTO get(String key) {
        return new UserDTO(userRepository.getUsers().stream().filter(user -> user.getCp().equalsIgnoreCase(key)).findFirst().orElse(new User()));
    }

    @Override
    public UserDTO getAt(int index) {
        return new UserDTO(userRepository.userAt(index));
    }

    public StudentDTO getStudent() {
        Student student = userRepository.getStudent();
        return (student != null) ? new StudentDTO(student) : null;
    }

    public List<StudentDTO> getStudents() {
        return userRepository.getUsers().stream().filter(user -> user instanceof Student).map(student -> new StudentDTO((Student) student)).toList();
    }

    public StudentDTO getRandomStudent() {
        List<StudentDTO> students = getStudents();
        return students.get(new Random().nextInt(students.size()));
    }

    public UserDTO getAdmin() {
        User admin = userRepository.getAdmin();
        return (admin != null) ? new UserDTO(admin) : null;
    }
}

package tech.salvas.eifapi.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tech.salvas.eifapi.models.Student;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {

    Optional<List<Student>> findStudents();

    Optional<Student> findStudentByEmailAndPassword(String email, String password);

    Optional<Student> findStudentByCpAndPassword(String cp, String password);

    Optional<Student> findStudentByCp(String cp);
}

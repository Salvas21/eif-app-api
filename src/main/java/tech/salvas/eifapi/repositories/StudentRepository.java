package tech.salvas.eifapi.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tech.salvas.eifapi.models.Student;

import java.util.Optional;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
    Optional<Student> findStudentByCp(String cp);

    Optional<Student> findStudentById(Long id);

    Optional<Student> findByEmailOrCp(String email, String cp);

}

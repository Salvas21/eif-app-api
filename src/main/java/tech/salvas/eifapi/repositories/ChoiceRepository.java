package tech.salvas.eifapi.repositories;

import org.springframework.data.repository.CrudRepository;
import tech.salvas.eifapi.models.Choice;
import tech.salvas.eifapi.models.Student;

import java.util.List;
import java.util.Optional;

public interface ChoiceRepository extends CrudRepository<Choice, String> {
    Optional<List<Choice>> findChoicesByStudentId(Student student);
}

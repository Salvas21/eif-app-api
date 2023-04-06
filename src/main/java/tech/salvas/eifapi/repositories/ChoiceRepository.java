package tech.salvas.eifapi.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tech.salvas.eifapi.models.Choice;
import tech.salvas.eifapi.models.Student;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChoiceRepository extends CrudRepository<Choice, Long> {
    Optional<List<Choice>> findChoices();
    @Override
    Optional<Choice> findById(Long id);
    Optional<List<Choice>> findChoicesByActivityId(Long activity_id);
    Optional<List<Choice>> findChoicesByStudentId(Student student);
}

package tech.salvas.eifapi.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tech.salvas.eifapi.models.Choice;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChoiceRepository extends CrudRepository<Choice, Long> {
    @Override
    Optional<Choice> findById(Long id);

    List<Choice> findChoicesByStudentId(Long studentId);

    void deleteAll();
}

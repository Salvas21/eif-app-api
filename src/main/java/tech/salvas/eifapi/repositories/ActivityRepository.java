package tech.salvas.eifapi.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tech.salvas.eifapi.models.Activity;

import java.util.List;
import java.util.Optional;

@Repository
public interface ActivityRepository extends CrudRepository<Activity, Long> {

    Optional<Activity> findActivityByCode(String code);
    Optional<List<Activity>> findActivitiesByLevelIdIsLessThanEqual(int levelId);

    Optional<Activity> findActivityById(Long id);
}

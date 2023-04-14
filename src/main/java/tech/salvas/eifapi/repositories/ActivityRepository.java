package tech.salvas.eifapi.repositories;

import org.hibernate.Hibernate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tech.salvas.eifapi.dtos.ActivityDTO;
import tech.salvas.eifapi.models.Activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface ActivityRepository extends CrudRepository<Activity, Long> {

    Optional<Activity> findActivityByCode(String code);

    Optional<List<Activity>> findActivitiesByLevelId(int level);

    Optional<List<Activity>> findActivitiesByLevelIdIsLessThanEqual(int level);

//    void updateActivityById(Long id);
//    boolean updateActivityByCode(String code, Activity activity);

//    void saveActivity(Activity activity);

}

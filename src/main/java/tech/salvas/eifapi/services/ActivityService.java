package tech.salvas.eifapi.services;

import org.springframework.stereotype.Service;
import tech.salvas.eifapi.dtos.ActivityDTO;
import tech.salvas.eifapi.models.Activity;
import tech.salvas.eifapi.repositories.ActivityRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ActivityService implements IActivityService {

    private ActivityRepository activityRepository;
    public ActivityService(ActivityRepository repository) {
        this.activityRepository = repository;
    }
    @Override
    public void save(ActivityDTO activityDTO) {
        activityRepository.add(activityDTO);
    }

    @Override
    public boolean delete(String code) {
        return false;
    }

    @Override
    public boolean update(ActivityDTO activityDTO, String code) {
        return activityRepository.modify(activityDTO, code);
    }

    @Override
    public List<ActivityDTO> getAll() {
        List<Activity> activities = activityRepository.getActivities();
        List<ActivityDTO> activityDTOS = new ArrayList<>();
        for (var activity: activities) {
            activityDTOS.add(new ActivityDTO(activity));
        }
        return activityDTOS;
    }

    @Override
    public ActivityDTO get(String code) {
        return new ActivityDTO(activityRepository.getActivity(code));
    }

    @Override
    public List<ActivityDTO> getCurrentForStudent(String cp) {
        List<Activity> activities = activityRepository.getActivitiesFor(cp);
        List<ActivityDTO> activityDTOS = new ArrayList<>();
        for (var activity: activities) {
            activityDTOS.add(new ActivityDTO(activity));
        }
        return activityDTOS;
    }

    @Override
    public List<ActivityDTO> getActivityForLevel(int level) {
        List<Activity> activities = activityRepository.getActivitiesForLevel(level);
        List<ActivityDTO> activityDTOS = new ArrayList<>();
        for (var activity: activities) {
            activityDTOS.add(new ActivityDTO(activity));
        }
        return activityDTOS;
    }

    @Override
    public List<String> getLevels() {
        return Arrays.asList("Débutant", "Intermédiaire", "Avancé");
    }
}

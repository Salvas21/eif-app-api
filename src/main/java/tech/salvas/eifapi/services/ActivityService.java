package tech.salvas.eifapi.services;

import org.springframework.stereotype.Service;
import tech.salvas.eifapi.dto.ActivityDTO;
import tech.salvas.eifapi.model.Activity;
import tech.salvas.eifapi.repository.ActivityRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ActivityService implements IActivityService {

    private ActivityRepository activityRepository;
    public ActivityService(ActivityRepository repository) {
        this.activityRepository = repository;
    }
    @Override
    public void save() {

    }

    @Override
    public void delete() {

    }

    @Override
    public void update() {

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
}

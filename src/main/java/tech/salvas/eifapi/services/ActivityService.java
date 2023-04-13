package tech.salvas.eifapi.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.salvas.eifapi.dtos.ActivityDTO;
import tech.salvas.eifapi.models.Activity;
import tech.salvas.eifapi.repositories.ActivityRepository;

import tech.salvas.eifapi.mappers.IActivityDTOActivityMapper;
import tech.salvas.eifapi.repositories.AttendanceRepository;
import tech.salvas.eifapi.repositories.StudentRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ActivityService implements IActivityService {

    private ActivityRepository activityRepository;

    private AttendanceRepository attendanceRepository;
    private StudentRepository studentRepository;

    private IActivityDTOActivityMapper mapper;

    @Override
    public void save(ActivityDTO activityDTO) {
//        activityRepository.add(activityDTO);
//        activityRepository.saveActivity(mapper.activityDTOToActivity(activityDTO));
        activityRepository.save(mapper.activityDTOToActivity(activityDTO));
    }

    @Override
    public boolean delete(String code) {
        return false;
    }

    @Override
    public boolean update(ActivityDTO activityDTO, String code) {
        Activity oldActivity = activityRepository.findActivityByCode(code).orElseThrow();
        Activity newActivity = mapper.activityDTOToActivity(activityDTO);
        newActivity.setId(oldActivity.getId());
        activityRepository.save(newActivity);
        return true;
//        return activityRepository.updateActivityByCode(code, mapper.activityDTOToActivity(activityDTO));
    }

    @Override
    public List<ActivityDTO> getAll() {
        List<ActivityDTO> activityDTOS = new ArrayList<>();
        for (Activity activity:  activityRepository.findAll()) {
            activityDTOS.add(mapper.activityToActivityDTO(activity));
        }
        return activityDTOS;
    }

    @Override
    public ActivityDTO get(String code) {
        var activity = activityRepository.findActivityByCode(code).orElse(null);
        return mapper.activityToActivityDTO(activity);
    }

    @Override
    public List<ActivityDTO> getCurrentForStudent(String cp) {
        List<ActivityDTO> activityDTOS = new ArrayList<>();
        var student = studentRepository.findStudentByCp(cp).orElse(null);
        for (var attendance: attendanceRepository.findAttendancesByStudentId(student).orElseThrow()) {
            activityDTOS.add(mapper.activityToActivityDTO(attendance.getActivity()));
        }
        return activityDTOS;
    }

    @Override
    public List<ActivityDTO> getActivityForLevel(int level) {
//        List<Activity> activities = activityRepository.getActivitiesForLevel(level);
//        Optional<List<Activity>> activities = activityRepository.findActivitiesByActivityLevel(level);
        List<ActivityDTO> activityDTOS = new ArrayList<>();
        for (var activity: activityRepository.findActivitiesByActivityLevelIsLessThanEqual(level).orElseThrow()) {
            activityDTOS.add(mapper.activityToActivityDTO(activity));
        }
        return activityDTOS;
    }

    @Override
    public List<String> getLevels() {
        return Arrays.asList("Débutant", "Intermédiaire", "Avancé");
    }
}

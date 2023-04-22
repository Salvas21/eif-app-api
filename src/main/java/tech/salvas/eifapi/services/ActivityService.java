package tech.salvas.eifapi.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.salvas.eifapi.dtos.ActivityDTO;
import tech.salvas.eifapi.models.Activity;
import tech.salvas.eifapi.repositories.ActivityRepository;

import tech.salvas.eifapi.mappers.ActivityMapper;
import tech.salvas.eifapi.repositories.AttendanceRepository;
import tech.salvas.eifapi.repositories.StudentRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ActivityService implements IActivityService {

    private ActivityRepository activityRepository;

    private AttendanceRepository attendanceRepository;
    private StudentRepository studentRepository;

    private ActivityMapper mapper = new ActivityMapper();

    public ActivityService(ActivityRepository repository, AttendanceRepository attendanceRepository, StudentRepository studentRepository) {
        this.activityRepository = repository;
        this.attendanceRepository = attendanceRepository;
        this.studentRepository = studentRepository;
    }


    @Override
    public void save(ActivityDTO activityDTO) {
        activityRepository.save(mapper.toEntity(activityDTO));
    }

    @Override
    public boolean delete(String code) {
        return false;
    }

    @Override
    public boolean update(ActivityDTO activityDTO, String code) {
        Activity oldActivity = activityRepository.findActivityByCode(code).orElseThrow();
        Activity newActivity = mapper.toEntity(activityDTO);
        newActivity.setId(oldActivity.getId());
        activityRepository.save(newActivity);
        return true;
    }

    @Override
    public List<ActivityDTO> getAll() {
        List<ActivityDTO> activityDTOS = new ArrayList<>();
        for (Activity activity:  activityRepository.findAll()) {
            activityDTOS.add(mapper.toDTO(activity));
        }
        return activityDTOS;
    }

    @Override
    public ActivityDTO get(String code) {
        var activity = activityRepository.findActivityByCode(code).orElse(null);
        return mapper.toDTO(activity);
    }

    @Override
    public List<ActivityDTO> getCurrentForStudent(String cp) {
        List<ActivityDTO> activityDTOS = new ArrayList<>();
        var student = studentRepository.findStudentByCp(cp).orElse(null);
        System.out.println(student);
        for (var attendance: attendanceRepository.findAttendancesByStudentId(student).orElseThrow()) {
//            activityDTOS.add(mapper.toDTO(attendance.getActivity()));
            activityDTOS.add(mapper.toDTO(activityRepository.findActivityByCode(attendance.getActivity().toString()).orElseThrow()));
        }
        return activityDTOS;
    }

    @Override
    public List<ActivityDTO> getActivityForLevel(int level) {
//        List<Activity> activities = activityRepository.getActivitiesForLevel(level);
//        Optional<List<Activity>> activities = activityRepository.findActivitiesByActivityLevel(level);
        List<ActivityDTO> activityDTOS = new ArrayList<>();
        for (var activity: activityRepository.findActivitiesByLevelIdIsLessThanEqual(level).orElseThrow()) {
            activityDTOS.add(mapper.toDTO(activity));
        }
        return activityDTOS;
    }

    @Override
    public List<String> getLevels() {
        return Arrays.asList("Débutant", "Intermédiaire", "Avancé");
    }
}

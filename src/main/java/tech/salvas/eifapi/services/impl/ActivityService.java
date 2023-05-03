package tech.salvas.eifapi.services.impl;

import org.springframework.stereotype.Service;
import tech.salvas.eifapi.dtos.ActivityDTO;
import tech.salvas.eifapi.models.Activity;
import tech.salvas.eifapi.repositories.ActivityRepository;

import tech.salvas.eifapi.mappers.ActivityMapper;
import tech.salvas.eifapi.repositories.AttendanceRepository;
import tech.salvas.eifapi.repositories.StudentRepository;
import tech.salvas.eifapi.services.IActivityService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ActivityService implements IActivityService {
    private final ActivityRepository activityRepository;
    private final AttendanceRepository attendanceRepository;
    private final StudentRepository studentRepository;
    private final ActivityMapper mapper = new ActivityMapper();

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
        for (Activity activity : activityRepository.findAll()) {
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
    public ActivityDTO getById(Long id) {
        return mapper.toDTO(activityRepository.findById(id).orElseThrow());
    }

    @Override
    public List<ActivityDTO> getCurrentForStudent(String cp) {
        List<ActivityDTO> activityDTOS = new ArrayList<>();
        var student = studentRepository.findStudentByCp(cp).orElse(null);
        for (var attendance : attendanceRepository.findAttendancesByStudentIdAndFinishedFalse(student.getId()).orElseThrow()) {
            activityDTOS.add(mapper.toDTO(activityRepository.findById(attendance.getActivityId()).orElseThrow()));
        }
        return activityDTOS;
    }

    @Override
    public List<ActivityDTO> getActivityAvailableForStudent(String cp) {
        List<ActivityDTO> activityDTOS = new ArrayList<>();
        var level = studentRepository.findStudentByCp(cp).orElseThrow().getLevelId();
        for (var activity : activityRepository.findActivitiesByLevelIdIsLessThanEqual(level.intValue()).orElseThrow()) {
            activityDTOS.add(mapper.toDTO(activity));
        }
        return activityDTOS;
    }

    @Override
    public List<String> getLevels() {
        return Arrays.asList("Débutant", "Intermédiaire", "Avancé");
    }
}

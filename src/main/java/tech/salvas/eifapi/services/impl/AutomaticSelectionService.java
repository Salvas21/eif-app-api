package tech.salvas.eifapi.services.impl;

import org.springframework.stereotype.Service;
import tech.salvas.eifapi.dtos.*;
import tech.salvas.eifapi.services.*;

import java.util.*;

@Service
public class AutomaticSelectionService implements IAutomaticSelectionService {
    private final IChoiceService choiceService;
    private final UserService userService;
    private final IActivityService activityService;
    private final IAttendanceService attendanceService;
    private final IMailService mailService;

    public AutomaticSelectionService(IChoiceService choiceService, UserService userService, IActivityService activityService,
                                     IAttendanceService attendanceService, IMailService mailService) {
        this.choiceService = choiceService;
        this.userService = userService;
        this.activityService = activityService;
        this.attendanceService = attendanceService;
        this.mailService = mailService;
    }

    @Override
    public List<StudentChoiceDTO> generateSelection() {
        // Get choices from DB
        List<ChoiceDTO> choices = this.choiceService.getAll();
        // Convert choices from DB to studentChoices
        List<StudentChoiceDTO> students = convertChoicesToStudentChoices(choices);
        // Assign the activities to students
        assignActivities(students);

        return students;
    }

    private List<StudentChoiceDTO> convertChoicesToStudentChoices(List<ChoiceDTO> choices) {
        Map<Long, StudentChoiceDTO> studentChoices = new HashMap<>();
        for (ChoiceDTO choice : choices) {
            if (studentChoices.containsKey(choice.getStudentId())) {
                studentChoices.get(choice.getStudentId()).getChoices().add(choiceActivityDTOFromChoiceDTO(choice));
            } else {
                StudentChoiceDTO sc = new StudentChoiceDTO(userService.getStudentById(choice.getStudentId()), new ArrayList<>());
                sc.getChoices().add(choiceActivityDTOFromChoiceDTO(choice));
                studentChoices.put(choice.getStudentId(), sc);
            }
        }

        return new ArrayList<>(studentChoices.values());
    }

    private void assignActivities(List<StudentChoiceDTO> students) {
        Map<Long, Integer> placeTracker = new HashMap<>();
        // Insure students are sorted by first come, first served
        students.sort(Comparator.comparingLong(a -> a.getStudent().getId()));

        // Assign activities to students
        for (StudentChoiceDTO student : students) {
            for (ChoiceActivityDTO choice : student.getChoices()) {
                // If first time seeing activity insert it into the place tracker
                placeTracker.putIfAbsent(choice.getActivity().getId(), 0);

                if (placeTracker.get(choice.getActivity().getId()) < choice.getActivity().getPlaces()) {
                    choice.setSelected(true);
                    // Increment the place tracker since activity has been assign for one more student
                    placeTracker.put(choice.getActivity().getId(), placeTracker.get(choice.getActivity().getId()) + 1);
                    break;
                }
            }
        }
    }

    private ChoiceActivityDTO choiceActivityDTOFromChoiceDTO(ChoiceDTO choiceDTO) {
        ActivityDTO activityDTO = activityService.getById(choiceDTO.getActivityId());

        ChoiceActivityDTO dto = new ChoiceActivityDTO();
        dto.setActivity(activityDTO);
        dto.setPreference(choiceDTO.getPreference());
        dto.setSelected(choiceDTO.isSelected());

        return dto;
    }

    @Override
    public void saveSelection(StudentChoiceDTO[] studentChoiceDTO, String session) {
        attendanceService.terminateCurrentAttendance();

        for (StudentChoiceDTO sc : studentChoiceDTO) {
            AttendanceDTO attendance = sc.getAttendance();
            attendance.setSession(session);
            // Save attendance in DB
            attendanceService.insert(attendance);
            // Send email to student
            mailService.sendActivitySelection(attendance);
        }

        choiceService.deleteAll();
    }
}

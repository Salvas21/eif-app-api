package tech.salvas.eifapi.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.salvas.eifapi.dtos.ActivityDTO;
import tech.salvas.eifapi.dtos.ChoiceActivityDTO;
import tech.salvas.eifapi.dtos.ChoiceDTO;
import tech.salvas.eifapi.dtos.StudentChoiceDTO;
import tech.salvas.eifapi.services.ActivityService;
import tech.salvas.eifapi.services.IChoiceService;
import tech.salvas.eifapi.services.UserService;

import java.util.*;

@RestController
@RequestMapping(path = "/api/auto-select")
public class AutomaticSelectionController {
    IChoiceService choiceService;
    UserService userService;
    ActivityService activityService;

    public AutomaticSelectionController(IChoiceService choiceService, UserService userService, ActivityService activityService) {
        this.choiceService = choiceService;
        this.userService = userService;
        this.activityService = activityService;
    }

    @CrossOrigin
    @GetMapping("/generate")
    public ResponseEntity<List<StudentChoiceDTO>> login() {
        List<ChoiceDTO> choices = this.choiceService.getAll();
        Map<Long, Integer> placeTracker = new HashMap<>();
        Map<Long, StudentChoiceDTO> studentChoices = new HashMap<>();

        for (ChoiceDTO choice : choices) {
            if (studentChoices.containsKey(choice.getStudentId())) {
                studentChoices.get(choice.getStudentId()).getChoices().add(choiceActivityDTOFromChoiceDTO(choice));
            } else {
                StudentChoiceDTO sc = new StudentChoiceDTO(userService.getStudentById(choice.getStudentId()), new ArrayList<>());
                sc.getChoices().add(choiceActivityDTOFromChoiceDTO(choice));
                studentChoices.put(choice.getStudentId(), sc);
            }
            // Init the place tracker by adding every activity that have inscriptions
            placeTracker.putIfAbsent(choice.getActivityId(), 0);
        }

        List<StudentChoiceDTO> students = new ArrayList<>(studentChoices.values());

        // Insure students are sorted by first come, first served
        students.sort(Comparator.comparingLong(a -> a.getStudent().getId()));

        for (StudentChoiceDTO student : students)
            for (ChoiceActivityDTO choice : student.getChoices()) {
                if (placeTracker.get(choice.getActivity().getId()) < choice.getActivity().getPlaces()) {
                    choice.setSelected(true);
                    placeTracker.put(choice.getActivity().getId(), placeTracker.get(choice.getActivity().getId()) + 1);
                    break;
                }
            }

        return ResponseEntity.ok(students);
    }

    private ChoiceActivityDTO choiceActivityDTOFromChoiceDTO(ChoiceDTO choiceDTO) {
        ActivityDTO activityDTO = activityService.getById(choiceDTO.getActivityId());

        ChoiceActivityDTO dto = new ChoiceActivityDTO();
        dto.setActivity(activityDTO);
        dto.setPreference(choiceDTO.getPreference());
        dto.setSelected(choiceDTO.isSelected());

        return dto;
    }
}

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        // TODO: implement automatic selection
        List<StudentChoiceDTO> students = new ArrayList<>(studentChoices.values());
        int i = 0;
        for (StudentChoiceDTO student : students)
            student.getChoices().get(i++ % student.getChoices().size()).setSelected(true);

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

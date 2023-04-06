package tech.salvas.eifapi.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.salvas.eifapi.dtos.ChoiceDTO;
import tech.salvas.eifapi.dtos.SlimStudentDTO;
import tech.salvas.eifapi.dtos.StudentChoiceDTO;
import tech.salvas.eifapi.dtos.StudentDTO;
import tech.salvas.eifapi.services.IChoiceService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/auto-select")
public class AutomaticSelectionController {
    IChoiceService choiceService;

    public AutomaticSelectionController(IChoiceService choiceService) {
        this.choiceService = choiceService;
    }

    @CrossOrigin
    @GetMapping("/generate")
    public ResponseEntity<List<StudentChoiceDTO>> login() {
        List<ChoiceDTO> choices = this.choiceService.getAll();
        Map<Long, StudentChoiceDTO> studentChoices = new HashMap<>();

        for (ChoiceDTO choice : choices) {
            if (studentChoices.containsKey(choice.getStudent().getId())) {
                studentChoices.get(choice.getStudent().getId()).getChoices().add(choice);
            } else {
                StudentChoiceDTO sc = new StudentChoiceDTO(new SlimStudentDTO(choice.getStudent()), new ArrayList<>());
                sc.getChoices().add(choice);
                studentChoices.put(choice.getStudent().getId(), sc);
            }
        }

        // TODO: implement automatic selection
        List<StudentChoiceDTO> students = new ArrayList<>(studentChoices.values());
        int i = 0;
        for (StudentChoiceDTO student : students)
            student.getChoices().get(i++ % student.getChoices().size()).setSelected(true);

        return ResponseEntity.ok(students);
    }
}

package tech.salvas.eifapi.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.salvas.eifapi.dto.StudentDTO;
import tech.salvas.eifapi.services.UserService;

import java.util.List;

@RestController
@RequestMapping(path = "/api/auto-select")
public class AutomaticSelectionController {

    UserService userService;

    public AutomaticSelectionController(UserService userService) {
        this.userService = userService;
    }

    @CrossOrigin
    @PostMapping("/generate")
    public ResponseEntity<List<StudentDTO>> login() {
        List<StudentDTO> students = this.userService.getStudents();
        int i = 0;
        for (StudentDTO student : students)
            student.getChoices().get(i++ % student.getChoices().size()).setSelected(true);

        return ResponseEntity.ok(students);
    }
}

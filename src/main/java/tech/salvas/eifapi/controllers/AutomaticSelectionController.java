package tech.salvas.eifapi.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.salvas.eifapi.dtos.StudentChoiceDTO;
import tech.salvas.eifapi.services.IAutomaticSelectionService;

import java.util.*;

@RestController
@RequestMapping(path = "/api/auto-select")
public class AutomaticSelectionController {
    private final IAutomaticSelectionService automaticSelectionService;

    public AutomaticSelectionController(IAutomaticSelectionService automaticSelectionService) {
        this.automaticSelectionService = automaticSelectionService;
    }

    @CrossOrigin
    @GetMapping("/generate")
    public ResponseEntity<List<StudentChoiceDTO>> generate() {
        return ResponseEntity.ok(automaticSelectionService.generateSelection());
    }

    @CrossOrigin
    @PostMapping(value = "/save")
    public ResponseEntity<String> save(@RequestBody SelectionWrapper wrapper) {
        try {
            this.automaticSelectionService.saveSelection(wrapper.students, wrapper.session);
            return ResponseEntity.ok("Ok");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    // Wrapper used to receive two different values from same request body
    //TODO: Extract class or keep private and internal?
    private static class SelectionWrapper {
        public StudentChoiceDTO[] students;
        public String session;
    }
}

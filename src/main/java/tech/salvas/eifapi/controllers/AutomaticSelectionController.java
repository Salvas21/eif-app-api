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
    public ResponseEntity<List<StudentChoiceDTO>> login() {
        return ResponseEntity.ok(automaticSelectionService.generateSelection());
    }
}

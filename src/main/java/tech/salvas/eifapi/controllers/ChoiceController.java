package tech.salvas.eifapi.controllers;

import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.salvas.eifapi.dtos.ActivityDTO;
import tech.salvas.eifapi.dtos.ChoiceDTO;
import tech.salvas.eifapi.services.ChoiceService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/choices")
public class ChoiceController {

    private final ChoiceService service;

    public ChoiceController(ChoiceService choiceService) {
        this.service = choiceService;
    }
    @CrossOrigin
    @PostMapping("/add")
    public ResponseEntity<String> addChoices(@RequestBody String choicesJson) {
        JsonParser springParser = JsonParserFactory.getJsonParser();
        var choices = springParser.parseList(choicesJson);
        for (var choiceX: choices) {
            Map<String,Object> choice = (Map<String,Object>) choiceX;
            this.service.save(choice.get("student").toString(), choice.get("activity").toString(), (int) choice.get("preference"));
        }
        return ResponseEntity.ok("");
    }

    @CrossOrigin
    @GetMapping("/get/{cp}")
    public ResponseEntity<List<ActivityDTO>> getChoicesOfStudentAsActivities(@PathVariable("cp") String cp) {
        return ResponseEntity.ok(service.getStudentChoicesAsActivities(cp));
    }
}

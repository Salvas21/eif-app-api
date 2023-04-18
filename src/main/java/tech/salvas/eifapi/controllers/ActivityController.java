package tech.salvas.eifapi.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.salvas.eifapi.dtos.ActivityDTO;
import tech.salvas.eifapi.services.IActivityService;

import java.util.List;

@RestController
@RequestMapping("/api/activity")
public class ActivityController {
    final IActivityService activityService;

    public ActivityController(IActivityService activityService) {
        this.activityService = activityService;
    }

    @CrossOrigin
    @GetMapping("/getAll")
    public ResponseEntity<List<ActivityDTO>> getAllActivities() {
        return ResponseEntity.ok(activityService.getAll());
    }

    @CrossOrigin
    @GetMapping("/get/{code}")
    public ResponseEntity<ActivityDTO> getActivity(@PathVariable String code) {
        var activity = activityService.get(code);
        return ResponseEntity.ok(activity);
    }

    @CrossOrigin
    @GetMapping("/getLevels")
    public ResponseEntity<List<String>> getLevels() {
        return ResponseEntity.ok(activityService.getLevels());
    }

    @CrossOrigin
    @GetMapping("/getAvailable/{levelName}")
    public ResponseEntity<?> getActivityForStudent(@PathVariable int levelName) {
        return ResponseEntity.ok(activityService.getActivityForLevel(levelName));
    }

    @CrossOrigin
    @GetMapping("/getCurrent/{cp}")
    public ResponseEntity<?> getActivityForStudent(@PathVariable String cp) {

        return ResponseEntity.ok(activityService.getCurrentForStudent(cp));
//        return ResponseEntity.ok(activityService.getActivityForLevel(levelName));
    }

    @CrossOrigin
    @PutMapping("/modify/{code}")
    public ResponseEntity<?> updateActivity(@RequestBody ActivityDTO activityDTO, @PathVariable String code) {
        var success = activityService.update(activityDTO, code);
        System.out.println("Received something");
        return ResponseEntity.ok("Activity ajouter: " + success);
    }

    @CrossOrigin
    @PostMapping("/add")
    public ResponseEntity<?> addActivity(@RequestBody ActivityDTO activity) {
        this.activityService.save(activity);
        return ResponseEntity.ok("Activity has been added");
    }
}

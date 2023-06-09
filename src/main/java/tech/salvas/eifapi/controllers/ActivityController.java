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
    @GetMapping("/getAvailable/{cp}")
    public ResponseEntity<?> getActivitiesAvailable(@PathVariable String cp) {
        return ResponseEntity.ok(activityService.getActivityAvailableForStudent(cp));
    }

    @CrossOrigin
    @GetMapping("/getCurrent/{cp}")
    public ResponseEntity<?> getActivityForStudent(@PathVariable String cp) {
        return ResponseEntity.ok(activityService.getCurrentForStudent(cp));
    }

    @CrossOrigin
    @PutMapping("/modify/{code}")
    public ResponseEntity<?> updateActivity(@RequestBody ActivityDTO activityDTO, @PathVariable String code) {
        var success = activityService.update(activityDTO, code);
        return ResponseEntity.ok("");
    }

    @CrossOrigin
    @PostMapping("/add")
    public ResponseEntity<String> addActivity(@RequestBody ActivityDTO activity) {
        this.activityService.save(activity);
        return ResponseEntity.ok("");
    }
}

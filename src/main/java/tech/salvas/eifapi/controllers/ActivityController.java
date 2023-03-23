package tech.salvas.eifapi.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.salvas.eifapi.dto.ActivityDTO;
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
    @GetMapping("/getForStudent/{code}/{studentId}")
    public ResponseEntity<?> getActivityForStudent(@PathVariable String code, @PathVariable String studentId) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not implemented");
    }

    @CrossOrigin
    @PutMapping("/put/{code}")
    public ResponseEntity<?> updateActivity(@PathVariable String code) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not implemented");
    }

    @CrossOrigin
    @PostMapping("/add")
    public ResponseEntity<?> addActivity() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not implemented");
    }
}

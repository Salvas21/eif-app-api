package tech.salvas.eifapi.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.salvas.eifapi.dtos.StudentDTO;
import tech.salvas.eifapi.services.UserService;

import java.util.List;

@RestController
@RequestMapping(path = "/api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @CrossOrigin
    @GetMapping("/students")
    public ResponseEntity<List<StudentDTO>> getStudents() {
        return ResponseEntity.ok(this.userService.getStudents());
    }
}

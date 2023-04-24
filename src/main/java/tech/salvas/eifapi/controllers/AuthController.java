package tech.salvas.eifapi.controllers;

import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.salvas.eifapi.dtos.UserDTO;
import tech.salvas.eifapi.services.UserService;

import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(path = "/api")
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @CrossOrigin
    @PostMapping("/auth")
    public ResponseEntity<?> login(@RequestBody String jsonString) {
        JsonParser springParser = JsonParserFactory.getJsonParser();
        Map<String, Object> credentials = springParser.parseMap(jsonString);
        try {
            UserDTO user = ((boolean) credentials.get("isAdmin"))
                ? this.userService.getAdmin(credentials.get("email").toString(), credentials.get("password").toString())
                : this.userService.getStudent(credentials.get("email").toString(), credentials.get("password").toString());

            return ResponseEntity.ok(user);
        } catch (NoSuchElementException exception) {
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Identifiants invalides");
        }
    }

    @CrossOrigin
    @GetMapping("/register")
    public ResponseEntity<String> register() {
        this.userService.registerAdmin();
        return ResponseEntity.ok("");
    }
}

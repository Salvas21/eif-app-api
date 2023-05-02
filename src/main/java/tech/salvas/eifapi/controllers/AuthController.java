package tech.salvas.eifapi.controllers;

import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.salvas.eifapi.dtos.AuthDTO;
import tech.salvas.eifapi.services.impl.UserService;

import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(path = "/api/auth")
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @CrossOrigin
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody String jsonString) {
        JsonParser springParser = JsonParserFactory.getJsonParser();
        Map<String, Object> credentials = springParser.parseMap(jsonString);
        try {
            String subject = credentials.get("email").toString();
            String password = credentials.get("password").toString();
            boolean isAdmin = (boolean) credentials.get("isAdmin");
            // if credentials don't match any of admin or students, returns a 403 forbidden
            AuthDTO authDTO = this.userService.authenticate(subject, password, isAdmin);
            return ResponseEntity.ok(authDTO);
        } catch (NoSuchElementException exception) {
            // happens when a student tries to log in as an admin or vice versa
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Identifiants invalides");
        }
    }
}

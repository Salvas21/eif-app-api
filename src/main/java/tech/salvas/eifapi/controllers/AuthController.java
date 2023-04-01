package tech.salvas.eifapi.controllers;

import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.salvas.eifapi.dto.StudentDTO;
import tech.salvas.eifapi.dto.UserDTO;
import tech.salvas.eifapi.model.Activity;
import tech.salvas.eifapi.model.Student;
import tech.salvas.eifapi.model.User;

import java.util.Map;

@RestController
@RequestMapping(path = "/api")
public class AuthController {

    @CrossOrigin
    @PostMapping("/auth")
    public ResponseEntity<UserDTO> login(@RequestBody String jsonString) {
        JsonParser springParser = JsonParserFactory.getJsonParser();
        Map<String, Object> credentials = springParser.parseMap(jsonString);

        UserDTO user = ((boolean)credentials.get("isAdmin"))
                ? new UserDTO(new User("Admin", "Name", "ADMIN12345678", true))
                : new StudentDTO(new Student("Martin", "Sandwich", "SANM12345678", 2, new Activity[] {new Activity("ACT12", "Test", "Description", "2")}));

        return ResponseEntity.ok(user);
    }
}

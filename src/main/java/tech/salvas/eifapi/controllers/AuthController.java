package tech.salvas.eifapi.controllers;

import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.salvas.eifapi.dtos.UserDTO;
import tech.salvas.eifapi.services.UserService;

import java.util.Map;

@RestController
@RequestMapping(path = "/api")
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @CrossOrigin
    @PostMapping("/auth")
    public ResponseEntity<UserDTO> login(@RequestBody String jsonString) throws Exception {
        JsonParser springParser = JsonParserFactory.getJsonParser();
        Map<String, Object> credentials = springParser.parseMap(jsonString);

        UserDTO user = ((boolean)credentials.get("isAdmin"))
                ? this.userService.getAdmin(credentials.get("email").toString(), credentials.get("password").toString())
                : this.userService.getStudent(credentials.get("email").toString(), credentials.get("password").toString());
        return ResponseEntity.ok(user);
    }
}

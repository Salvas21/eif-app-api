package tech.salvas.eifapi.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tech.salvas.eifapi.services.FileService;

import java.io.IOException;

@RestController
@RequestMapping(path = "/api/files")
public class FileController {

    final FileService fileService;

    public FileController(FileService fileService) {
        // https://github.com/gladius/spring-boot-digital-ocean-spaces
        this.fileService = fileService;
    }


    @CrossOrigin
    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file, @RequestParam("activityID") String activityID) {
        // id
        // atelier-id
        // filename
        // link / cdn-name
        if (file.getSize() == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File is invalid");
        }

        // 50MB
        if (file.getSize() > 50000000) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File is too large (Maximum file size: 50MB)");
        }



        try {
//            this.fileService.save(file, "test");
            this.fileService.save(file, activityID);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).body("File uploaded");
    }
}

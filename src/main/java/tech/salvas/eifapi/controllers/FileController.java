package tech.salvas.eifapi.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tech.salvas.eifapi.dto.FileDTO;
import tech.salvas.eifapi.model.File;
import tech.salvas.eifapi.services.IFileService;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/files")
public class FileController {

    private final IFileService fileService;
    private final ModelMapper modelMapper;

    public FileController(IFileService IFileService, ModelMapper modelMapper) {
        // https://github.com/gladius/spring-boot-digital-ocean-spaces
        this.fileService = IFileService;
        this.modelMapper = modelMapper;
    }


    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file, @RequestParam("activityID") String activityID) {
        if (file.getSize() == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File is invalid");
        }

        // 50MB
        if (file.getSize() > 50000000) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File is too large (Maximum file size: 50MB)");
        }

        try {
            fileService.save(file, activityID);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).body("File uploaded");
    }

    @GetMapping("/get/{activityID}")
    public ResponseEntity<List<FileDTO>> getAll(@PathVariable("activityID") String activityID) {
        List<File> files = fileService.getAll(activityID);

        return ResponseEntity.ok(files.stream().map(post -> modelMapper.map(post, FileDTO.class)).collect(Collectors.toList()));
    }

    @GetMapping("/get/{activityID}/download/{key}")
    public ResponseEntity<String> get(@PathVariable("activityID") String activityID, @PathVariable("key") String key) {
        String url = fileService.get(activityID, key);
        return ResponseEntity.ok(url);
    }

    @GetMapping("/get/{activityID}/delete/{key}")
    public ResponseEntity<String> delete(@PathVariable("activityID") String activityID, @PathVariable("key") String key) {
        fileService.delete(activityID, key);
        return ResponseEntity.ok("");
    }
}

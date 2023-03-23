package tech.salvas.eifapi.controllers;

import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;

@RestController
@RequestMapping(path = "/api")
public class TestController {

    @GetMapping("/")
    public ResponseEntity<String> test() {
        return new ResponseEntity<>("Hello world !", HttpStatus.OK);
    }

}

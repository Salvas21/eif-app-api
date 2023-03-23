package tech.salvas.eifapi.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface FileService {
    void save(MultipartFile multipartFile, String activityID) throws IOException;

    void delete(int id) throws Exception;

    List<File> getAll();
    File get(int id);
}

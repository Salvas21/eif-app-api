package tech.salvas.eifapi.services;

import org.springframework.web.multipart.MultipartFile;
import tech.salvas.eifapi.models.File;

import java.io.IOException;
import java.util.List;

public interface IFileService {
    void save(MultipartFile multipartFile, String activityID) throws IOException;

    void delete(String activityID, String key);

    List<File> getAll(String activityID);

    String get(String activityID, String key);
}

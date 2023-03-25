package tech.salvas.eifapi.services;

import org.springframework.web.multipart.MultipartFile;
import tech.salvas.eifapi.model.File;

import java.io.IOException;
import java.util.List;

public interface IFileService {
    void save(MultipartFile multipartFile, String activityID) throws IOException;

    void delete(int id) throws Exception;

    List<File> getAll(String activityID);
    File get(String activityID, String key);
}

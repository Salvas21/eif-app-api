package tech.salvas.eifapi.model;

import lombok.Data;
import tech.salvas.eifapi.dto.FileDTO;

@Data
public class File {
    private String key;
    private String name;
    private String size;
    private String lastModified;

    public File(String key, String size, String lastModified) {
        this.key = key;
        this.name = generateName(key);
        this.size = size;
        this.lastModified = lastModified;
    }

    public File(FileDTO fileDTO) {
        this.key = fileDTO.getKey();
        this.name = generateName(fileDTO.getName());
        this.size = fileDTO.getSize();
        this.lastModified = fileDTO.getLastModified();
    }

    private String generateName(String key) {
        return key.substring(key.lastIndexOf("/") + 1).trim();
    }
}

package tech.salvas.eifapi.dto;

import lombok.Data;
import tech.salvas.eifapi.model.File;

@Data
public class FileDTO {
    private String key;
    private String name;
    private String size;
    private String lastModified;

    public FileDTO(File file) {
        this.key = file.getKey();
        this.name = file.getName();
        this.size = file.getSize();
        this.lastModified = file.getLastModified();
    }

    public FileDTO() {
    }

}

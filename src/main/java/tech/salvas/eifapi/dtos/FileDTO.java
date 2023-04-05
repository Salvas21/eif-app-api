package tech.salvas.eifapi.dtos;

import lombok.Data;
import tech.salvas.eifapi.models.File;

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
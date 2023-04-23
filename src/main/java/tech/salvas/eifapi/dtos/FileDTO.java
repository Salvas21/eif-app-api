package tech.salvas.eifapi.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FileDTO {
    private String key;
    private String name;
    private String size;
    private String lastModified;
}

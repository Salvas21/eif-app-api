package tech.salvas.eifapi.mappers;

import org.apache.commons.lang3.NotImplementedException;
import tech.salvas.eifapi.dtos.FileDTO;
import tech.salvas.eifapi.models.File;

public class FileMapper implements Mapper<File, FileDTO> {
    @Override
    public FileDTO toDTO(File file) {
        var dto = new FileDTO();
        dto.setName(file.getName());
        dto.setKey(file.getKey());
        dto.setSize(file.getSize());
        dto.setLastModified(file.getLastModified());
        return dto;
    }

    @Override
    public File toEntity(FileDTO fileDTO) {
        throw new NotImplementedException();
    }
}

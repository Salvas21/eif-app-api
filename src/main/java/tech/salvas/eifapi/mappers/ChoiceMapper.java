package tech.salvas.eifapi.mappers;

import tech.salvas.eifapi.dtos.ActivityDTO;
import tech.salvas.eifapi.dtos.ChoiceDTO;
import tech.salvas.eifapi.models.Choice;

public class ChoiceMapper implements Mapper<Choice, ChoiceDTO> {
    private ActivityMapper activityMapper = new ActivityMapper();
    private StudentMapper studentMapper = new StudentMapper();

    public ChoiceDTO toDTO(Choice choice) {
        var dto = new ChoiceDTO(choice);
        return dto;
    }
    public Choice toEntity(ChoiceDTO choiceDTO) {
        var entity = new Choice();
//        entity.setActivity(activityMapper.toEntity(choiceDTO.getActivity()));
        entity.setSelected(choiceDTO.isSelected());
        entity.setPreference(choiceDTO.getPreference());
//        entity.setStudentId(studentMapper.toEntity(choiceDTO.getStudent()));
        return entity;
    }
}

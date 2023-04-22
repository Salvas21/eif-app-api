package tech.salvas.eifapi.mappers;

import tech.salvas.eifapi.dtos.ChoiceDTO;
import tech.salvas.eifapi.models.Choice;

public class ChoiceMapper implements Mapper<Choice, ChoiceDTO> {

    public ChoiceDTO toDTO(Choice choice) {
        ChoiceDTO choiceDTO = new ChoiceDTO();
        choiceDTO.setSelected(choice.isSelected());
        choiceDTO.setPreference(choice.getPreference());
        choiceDTO.setStudentId(choice.getStudentId());
        choiceDTO.setActivityId(choice.getActivityId());
        return choiceDTO;
    }
    public Choice toEntity(ChoiceDTO choiceDTO) {
        var entity = new Choice();
        entity.setActivityId(choiceDTO.getActivityId());
        entity.setSelected(choiceDTO.isSelected());
        entity.setPreference(choiceDTO.getPreference());
        entity.setStudentId(choiceDTO.getActivityId());
        return entity;
    }
}

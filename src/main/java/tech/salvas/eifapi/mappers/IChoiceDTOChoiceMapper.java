package tech.salvas.eifapi.mappers;

import org.mapstruct.Mapper;
import tech.salvas.eifapi.dtos.ChoiceDTO;
import tech.salvas.eifapi.models.Choice;

@Mapper(componentModel = "spring")
public interface IChoiceDTOChoiceMapper {
    ChoiceDTO choiceToChoiceDTO(Choice choice);
    Choice choiceDTOToChoice(ChoiceDTO choiceDTO);
}

package tech.salvas.eifapi.mappers;

import tech.salvas.eifapi.dtos.ChoiceDTO;
import tech.salvas.eifapi.models.Choice;

public interface IChoiceDTOChoiceMapper {
    ChoiceDTO choiceToChoiceDTO(Choice choice);
    Choice choiceDTOToChoice(ChoiceDTO choiceDTO);
}

package tech.salvas.eifapi.services;

import tech.salvas.eifapi.dtos.StudentChoiceDTO;

import java.util.List;

public interface IAutomaticSelectionService {
    List<StudentChoiceDTO> generateSelection();

    void saveSelection();
}

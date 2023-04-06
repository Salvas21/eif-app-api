package tech.salvas.eifapi.services;

import tech.salvas.eifapi.dtos.ChoiceDTO;

import java.util.List;

public interface IChoiceService {
    void save();
    void delete(long id);
    void update();
    List<ChoiceDTO> getAll();
    ChoiceDTO get(long key);
}

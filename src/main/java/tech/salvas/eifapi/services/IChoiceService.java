package tech.salvas.eifapi.services;

import tech.salvas.eifapi.dtos.ChoiceDTO;

import java.util.List;

public interface IChoiceService {
    void save(String cp, String code, int preference);
    void delete(long id);
    void update();
    List<ChoiceDTO> getAll();
    ChoiceDTO get(long key);
}

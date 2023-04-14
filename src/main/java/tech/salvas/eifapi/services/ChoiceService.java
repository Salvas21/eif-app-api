package tech.salvas.eifapi.services;

import org.springframework.stereotype.Service;
import tech.salvas.eifapi.dtos.ChoiceDTO;
import tech.salvas.eifapi.models.Choice;
import tech.salvas.eifapi.repositories.ChoiceRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChoiceService implements IChoiceService {
    private ChoiceRepository choiceRepository;

    public ChoiceService(ChoiceRepository choiceRepository) {
        this.choiceRepository = choiceRepository;
    }

    @Override
    public void save() {

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public void update() {

    }

    @Override
    public List<ChoiceDTO> getAll() {
        var choicesList = new ArrayList<Choice>();
        choiceRepository.findAll().iterator().forEachRemaining(choicesList::add);
        // TODO : change DTO
        return choicesList.stream().map(ChoiceDTO::new).toList();
    }

    @Override
    public ChoiceDTO get(long key) {
        return choiceRepository.findById(key).map(ChoiceDTO::new).orElse(null);
    }
}

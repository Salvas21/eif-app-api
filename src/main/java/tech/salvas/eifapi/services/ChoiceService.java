package tech.salvas.eifapi.services;

import org.springframework.stereotype.Service;
import tech.salvas.eifapi.dtos.ChoiceDTO;
import tech.salvas.eifapi.repositories.ChoiceRepository;

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
        return choiceRepository.findChoices().map(choices -> choices.stream().map(ChoiceDTO::new).toList()).orElse(null);
    }

    @Override
    public ChoiceDTO get(long key) {
        return choiceRepository.findById(key).map(ChoiceDTO::new).orElse(null);
    }
}

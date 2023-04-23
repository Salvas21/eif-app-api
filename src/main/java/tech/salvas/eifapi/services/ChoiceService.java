package tech.salvas.eifapi.services;

import org.springframework.stereotype.Service;
import tech.salvas.eifapi.dtos.ChoiceDTO;
import tech.salvas.eifapi.mappers.ActivityMapper;
import tech.salvas.eifapi.mappers.ChoiceMapper;
import tech.salvas.eifapi.mappers.ChoiceMapper;
import tech.salvas.eifapi.models.Choice;
import tech.salvas.eifapi.repositories.ActivityRepository;
import tech.salvas.eifapi.repositories.ChoiceRepository;
import tech.salvas.eifapi.repositories.StudentRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChoiceService implements IChoiceService {
    private ChoiceRepository choiceRepository;
    private StudentRepository studentRepository;
    private ActivityRepository activityRepository;

    private ChoiceMapper choiceMapper;

    public ChoiceService(ChoiceRepository choiceRepository, StudentRepository studentRepository, ActivityRepository activityRepository) {
        this.choiceRepository = choiceRepository;
        this.choiceMapper = new ChoiceMapper();
        this.studentRepository = studentRepository;
        this.activityRepository = activityRepository;
    }

    @Override
    public void save(String cp, String code, int preference) {
        var studentId = studentRepository.findStudentByCp(cp).orElseThrow().getId();
        var activityId = activityRepository.findActivityByCode(code).orElseThrow().getId();
        choiceRepository.save(choiceMapper.toEntity(activityId, studentId, preference));
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
        return choicesList.stream().map(choiceMapper::toDTO).toList();
    }

    @Override
    public ChoiceDTO get(long key) {
        return choiceRepository.findById(key).map(choiceMapper::toDTO).orElse(null);
    }
}

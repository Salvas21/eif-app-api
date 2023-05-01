package tech.salvas.eifapi.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import tech.salvas.eifapi.dtos.AttendanceDTO;
import tech.salvas.eifapi.mappers.AttendanceMapper;

@Repository
public class AttendanceInsertRepository {
    private final AttendanceMapper mapper = new AttendanceMapper();

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void insertWithEntityManager(AttendanceDTO attendance) {
        this.entityManager.persist(mapper.toEntity(attendance));
    }
}

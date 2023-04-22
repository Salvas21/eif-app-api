package tech.salvas.eifapi.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tech.salvas.eifapi.models.Attendance;
import tech.salvas.eifapi.models.Student;

import java.util.List;
import java.util.Optional;
@Repository
public interface AttendanceRepository extends CrudRepository<Attendance, Long> {
    Optional<List<Attendance>> findAttendancesByStudentId(Long id);
}

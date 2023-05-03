package tech.salvas.eifapi.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tech.salvas.eifapi.models.Attendance;

import java.util.List;
import java.util.Optional;

@Repository
public interface AttendanceRepository extends CrudRepository<Attendance, Long> {
    Optional<List<Attendance>> findAttendancesByStudentId(Long id);

    Optional<List<Attendance>> findAttendancesByStudentIdAndFinishedFalse(Long id);

    @Transactional
    @Modifying
    @Query("update Attendance a set a.finished = true")
    void terminateCurrentAttendance();
}

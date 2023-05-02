package tech.salvas.eifapi.services.impl;

import org.springframework.stereotype.Service;
import tech.salvas.eifapi.dtos.AttendanceDTO;
import tech.salvas.eifapi.repositories.AttendanceInsertRepository;
import tech.salvas.eifapi.repositories.AttendanceRepository;
import tech.salvas.eifapi.services.IAttendanceService;

@Service
public class AttendanceService implements IAttendanceService {
    private final AttendanceRepository attendanceRepository;
    private final AttendanceInsertRepository attendanceInsertRepository;

    public AttendanceService(AttendanceRepository attendanceRepository, AttendanceInsertRepository attendanceInsertRepository) {
        this.attendanceRepository = attendanceRepository;
        this.attendanceInsertRepository = attendanceInsertRepository;
    }

    @Override
    public void terminateCurrentAttendance() {
        attendanceRepository.terminateCurrentAttendance();
    }

    @Override
    public void insert(AttendanceDTO attendance) {
        attendanceInsertRepository.insertWithEntityManager(attendance);
    }
}

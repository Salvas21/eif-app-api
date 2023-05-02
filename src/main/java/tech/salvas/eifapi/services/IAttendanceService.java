package tech.salvas.eifapi.services;

import tech.salvas.eifapi.dtos.AttendanceDTO;

public interface IAttendanceService {
    void terminateCurrentAttendance();

    void insert(AttendanceDTO attendance);
}

package tech.salvas.eifapi.services;

import tech.salvas.eifapi.dtos.AttendanceDTO;

public interface IMailService {
    void sendActivitySelection(AttendanceDTO attendance);
}

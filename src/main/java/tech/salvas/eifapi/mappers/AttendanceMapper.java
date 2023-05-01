package tech.salvas.eifapi.mappers;

import tech.salvas.eifapi.dtos.AttendanceDTO;
import tech.salvas.eifapi.models.Attendance;

public class AttendanceMapper implements Mapper<Attendance, AttendanceDTO> {
    @Override
    public AttendanceDTO toDTO(Attendance attendance) {
        return new AttendanceDTO(attendance.getActivityId(), attendance.getStudentId(), attendance.getSession(), attendance.isFinished());
    }

    @Override
    public Attendance toEntity(AttendanceDTO dto) {
        Attendance attendance = new Attendance();
        attendance.setActivityId(dto.getActivityId());
        attendance.setStudentId(dto.getStudentId());
        attendance.setSession(dto.getSession());
        attendance.setFinished(dto.isFinished());
        return attendance;
    }
}

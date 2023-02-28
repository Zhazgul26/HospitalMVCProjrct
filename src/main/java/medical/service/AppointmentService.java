package medical.service;

import medical.entity.Appointment;
import medical.entity.Hospital;
import medical.entity.Patient;

import java.util.List;

public interface AppointmentService {


    void save(Long hospitalId,Long patientId, Long doctorId, Long departmentId,Appointment appointment);

    Appointment getById(Long id);

    List<Appointment> getAll(Long id);

    Appointment update(Long hospitalId, Long patientId, Long doctorId, Long departmentId,Appointment appointment,Long appointmentId);

    void delete(Long appointmentId);

}
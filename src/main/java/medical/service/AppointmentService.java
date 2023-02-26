package medical.service;

import medical.entity.Appointment;
import medical.entity.Hospital;
import medical.entity.Patient;

import java.util.List;

public interface AppointmentService {


    void save(Long id,Appointment appointment);

    Appointment getById(Long id);

    List<Appointment> getAll(Long id);

    void update(Long id, Appointment newAppointment);

    void delete(Long id);

}
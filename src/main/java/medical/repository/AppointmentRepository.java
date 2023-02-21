package medical.repository;

import medical.entity.Appointment;

import java.util.List;

public interface AppointmentRepository {

    Appointment save(Appointment appointment);
    List<Appointment> getAll();
    void deleteById(Long id);
    Appointment getById(Long id);
    void update (Long id, Appointment newAppointment);
}

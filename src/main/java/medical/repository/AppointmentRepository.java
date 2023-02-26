package medical.repository;

import medical.entity.Appointment;

import java.util.List;

public interface AppointmentRepository {

    String save(Appointment appointment);

    Appointment getById(Long id);

    List<Appointment> getAll(Long id);

    void update(Long id, Appointment newAppointment);

    void delete(Long id);
}
